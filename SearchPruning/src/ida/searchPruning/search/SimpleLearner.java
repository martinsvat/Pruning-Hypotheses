/*
 * Copyright (c) 2015 Ondrej Kuzelka
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package ida.searchPruning.search;

import ida.ilp.logic.*;
import ida.ilp.logic.HornClause;
import ida.ilp.logic.special.IsoClauseWrapper;
import ida.ilp.logic.subsumption.Matching;
import ida.searchPruning.evaluation.BreadthResults;
import ida.searchPruning.search.criterion.Accuracy;
import ida.searchPruning.search.criterion.Criterion;
import ida.searchPruning.search.collections.*;
import ida.utils.Sugar;
import ida.utils.collections.MultiMap;
import ida.utils.tuples.Pair;
import logicStuff.learning.datasets.DatasetInterface;
import logicStuff.learning.datasets.MEDataset;
import logicStuff.learning.saturation.RuleSaturator;
import logicStuff.theories.TheorySimplifier;
import logicStuff.theories.TheorySolver;

import ida.utils.TimeDog;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Does not handle constants. Constants need to be handled as unary literals. But you're free to implement this functionality :)
 * <p>
 * Created by kuzelkao_cardiff on 20/01/17 and extended by MS.
 */
public class SimpleLearner {

    private final List<Clause> constraints;
    private final Set<Integer> learnFrom;
    private final TimeDog time;
    private final MutableStats stats;
    private final RuleSaturator saturator;

    private boolean connectedOnly = true;

    private final int minSupport;

    DatasetInterface dataset;

    private Set<Pair<String, Integer>> allAllowedPredicates;

    public static boolean VERBOUSE = false;

    private final Criterion criterion;

    public SimpleLearner(DatasetInterface dataset, List<Clause> constraints, Set<Integer> learnFrom, Criterion criterion, TimeDog time, MutableStats stats, int minSupport) {
        this.dataset = dataset;
        this.allAllowedPredicates = dataset.allPredicates();
        this.constraints = constraints;
        this.learnFrom = Collections.unmodifiableSet(new HashSet<>(learnFrom));
        this.criterion = criterion;
        this.time = time;
        this.stats = stats;
        this.minSupport = minSupport;
        this.saturator = new RuleSaturator(constraints, Matching.OI_SUBSUMPTION);
    }


    //+1 = positive classifier, -1 = negative classifier, 0 = both
    public SearchNodeInfo bestFirstSearch(int maxNodesSearched, TimeDog ruleLearningTime) {
        MultiMap<HornClause, Literal> badRefinements = new MultiMap<HornClause, Literal>();
        CollectionWithMemory<SearchNodeInfo> queue = new CollectionWithMemory<>(new QueueCollection<SearchNodeInfo>());

        Pair<Set<Integer>, Set<Integer>> data = selectExample();
        Set<Integer> pos = data.r;
        Set<Integer> neg = data.s;
        HornClause empty = new HornClause(new Clause());
        queue.forcePush(new SearchNodeInfo(pos, neg, empty, computeAccuracy(pos, neg, empty), computeAllowablitiy(pos, neg, empty), learnFrom, Sugar.list()));

        SearchNodeInfo best = null;
        for (int i = 1; i <= maxNodesSearched && queue.isNotEmpty() && time.enough() && ruleLearningTime.enough(); i++) {
            SearchNodeInfo head = queue.poll();
            if (VERBOUSE) {
                System.out.println("resolving node\t" + i + "\t" + queue.size() + "\tleft time " + time.left());
            }
            if (head.getCovered().size() < 2) {
                continue;
            }

            List<HornClause> candidates = refinements(head.getRule(), badRefinements, head.getCovered());

            Pair<List<HornClause>, Set<Literal>> filteredWithBadRefirements = filterCandidates(candidates, head.getRule().toClause());
            candidates = filteredWithBadRefirements.r;

            for (HornClause horn : candidates) {
                SearchNodeInfo evaluated = evaluate(horn, head.getCovered());
                if (evaluated.getPositiveCoveredExamples().size() < 1) {
                    continue;
                }
                queue.push(evaluated);
                badRefinements.putAll(horn, badRefinements.get(head.getRule()));
                badRefinements.putAll(horn, filteredWithBadRefirements.s);
                best = update(best, evaluated);
            }
        }
        best.setLengths(queue.getMemory().stream().map(hypotheses -> new Long(hypotheses.getRule().body().literals().size())).collect(Collectors.toList()));
        return best;
    }


    public SearchNodeInfo beamSearch(int maxDepth, int beamWidth, TimeDog ruleLearningTime) {
        // tod extend bad refinements by upp (???)
        MultiMap<HornClause, Literal> badRefinements = new MultiMap<>();
        CollectionWithMemory<SearchNodeInfo> queue = new CollectionWithMemory<>(new QueueCollection<SearchNodeInfo>(), beamWidth);

        Pair<Set<Integer>, Set<Integer>> data = selectExample();
        Set<Integer> pos = data.r;
        Set<Integer> neg = data.s;
        HornClause empty = new HornClause(new Clause());
        queue.forcePush(new SearchNodeInfo(pos, neg, empty, computeAccuracy(pos, neg, empty), computeAllowablitiy(pos, neg, empty), learnFrom, Sugar.list()));


        SearchNodeInfo best = null;
        for (int i = 1; i <= maxDepth && queue.isNotEmpty() && time.enough() && ruleLearningTime.enough(); i++) {
            CollectionWithMemory<SearchNodeInfo> next = new CollectionWithMemory<>(new QueueCollection<SearchNodeInfo>());
            next.addMemory(queue.getMemory());

            if (VERBOUSE) {
                System.out.println("resolving depth\t" + i + "\t" + queue.size() + "\tleft time " + time.left());
            }
            while (queue.isNotEmpty() && time.enough() && ruleLearningTime.enough()) {
                SearchNodeInfo head = queue.poll();

                if (head.getCovered().size() < 2) {
                    continue;
                }

                List<HornClause> candidates = refinements(head.getRule(), badRefinements, head.getCovered());
                Pair<List<HornClause>, Set<Literal>> filteredWithBadRefinements = filterCandidates(candidates, head.getRule().toClause());
                candidates = filteredWithBadRefinements.r;

                for (HornClause horn : candidates) {
                    SearchNodeInfo evaluated = evaluate(horn, head.getCovered());
                    if (evaluated.getPositiveCoveredExamples().size() < 1) {
                        continue;
                    }
                    next.push(evaluated);
                    badRefinements.putAll(horn, badRefinements.get(head.getRule()));
                    badRefinements.putAll(horn, filteredWithBadRefinements.s);
                    best = update(best, evaluated);
                }
            }
            queue = next;
            queue.checkSize();
        }
        best.setLengths(queue.getMemory().stream()
                .map(hypotheses -> new Long(hypotheses.getRule().body().literals().size()))
                .collect(Collectors.toList()));
        return best;
    }

    private SearchNodeInfo update(SearchNodeInfo best, SearchNodeInfo head) {
        if (null == head) {
            return best;
        }
        if ((null == best || best.getAccuracy() < head.getAccuracy())
                && head.isAllowed()
                && head.getNumberOfCovered() > 0) {
            return head;
        }
        return best;
    }

    private Pair<Set<Integer>, Set<Integer>> selectExample() {
        Set<Integer> pos = dataset.getPosIdxs();
        Set<Integer> neg = new HashSet<>(dataset.getNegIdxs());
        pos.retainAll(learnFrom);
        neg.retainAll(learnFrom);
        return new Pair<>(pos, neg);
    }

    public BreadthResults breadthFirstSearch(int maxDepth, long nanoSearchStart, long constraintTime) {
        MultiMap<HornClause, Literal> badRefinements = new MultiMap<>();
        CollectionWithMemory<SearchNodeInfo> list = new CollectionWithMemory<>(new ListCollection<SearchNodeInfo>());

        BreadthResults result = new BreadthResults(nanoSearchStart);

        Pair<Set<Integer>, Set<Integer>> data = selectExample();
        Set<Integer> pos = data.r;
        Set<Integer> neg = data.s;
        HornClause empty = new HornClause(new Clause());
        list.forcePush(new SearchNodeInfo(pos, neg, empty, computeAccuracy(pos, neg, empty), computeAllowablitiy(pos, neg, empty), learnFrom));

        Map<Integer, CollectionWithMemory> history = new HashMap<>();
        history.put(0, list);
        result.join(0, new HashSet<>(list.getMemory()), stats, constraintTime);
        for (int i = 1; i <= maxDepth && list.isNotEmpty() && time.enough(); i++) {
            CollectionWithMemory<SearchNodeInfo> next = new CollectionWithMemory<>(new ListCollection<SearchNodeInfo>());
            next.addMemory(list.getMemory());
            if (VERBOUSE) {
                System.out.println("resolving depth\t" + i + "\t" + list.size() + "\tleft time " + time.left());
            }
            while (list.isNotEmpty() && time.enough()) {
                SearchNodeInfo head = list.poll();

                if (VERBOUSE) {
                    System.out.println("resolving\t" + head);
                }

                if (head.getNumberOfCovered() < 2) {
                    continue;
                }

                List<HornClause> candidates = refinements(head.getRule(), badRefinements, head.getCovered());
                Pair<List<HornClause>, Set<Literal>> filteredWithBadRefirements = filterCandidates(candidates, head.getRule().toClause());
                candidates = filteredWithBadRefirements.r;

                candidates.stream().forEach(horn -> {
                    SearchNodeInfo evaluated = evaluate(horn, head.getCovered());
                    if (evaluated.getPositiveCoveredExamples().size() > 0) {
                        next.push(evaluated);
                        badRefinements.putAll(horn, badRefinements.get(head.getRule()));
                        badRefinements.putAll(horn, filteredWithBadRefirements.s);
                    }
                });
            }

            result.join(i, new HashSet<>(next.getMemory()), stats, constraintTime);
            history.put(i, next);
            list = next;
            System.out.println("size\t" + list.size());
        }
        System.out.println("successors");
        int previous = 0;
        for (int idx = 0; idx <= maxDepth; idx++) {
            if (history.keySet().contains(idx)) {
                int current = history.get(idx).getMemory().size();
                System.out.println("\t" + idx + " " + current + " -- " + (current - previous));
                previous = history.get(idx).getMemory().size();
            }
        }
        return result;
    }

    private double computeAccuracy(Set<Integer> pos, Set<Integer> neg, HornClause empty) {
        return null == criterion ? 0.0d : criterion.compute(pos.size(), neg.size(), empty);
    }

    private boolean computeAllowablitiy(Set<Integer> pos, Set<Integer> neg, HornClause empty) {
        return null == criterion ? true : criterion.isAllowed(pos.size(), neg.size(), empty);
    }

    private Pair<List<HornClause>, Set<Literal>> filterCandidates(List<HornClause> candidates, Clause originalClause) {
        Pair<List<HornClause>, Set<Literal>> saturated = saturationRefinement(candidates, this.constraints, originalClause);
        int saturatedSize = saturated.r.size();
        candidates = filterIsomorphic(saturated.r);
        int unique = candidates.size();
        stats.nodesPruned(saturatedSize - unique);
        return new Pair<>(candidates, saturated.s);
    }

    private Pair<List<HornClause>, Set<Literal>> saturationRefinement(List<HornClause> candidates, List<Clause> constraints, Clause originalClause) {
        if (constraints.isEmpty()) {
            return new Pair<>(candidates, new HashSet<>());
        }

        List<HornClause> saturated = Sugar.list();
        Set<Literal> badRefinement = Sugar.set();

        for (HornClause candidate : candidates) {
            //
            //HornClause extended = saturationRefinement(candidate, constraints);
            HornClause extended = saturator.saturate(candidate);
            if (null == extended) {
                stats.hypothesesKilled();
                badRefinement.addAll(Sugar.setDifference(candidate.toClause().literals(), originalClause.literals()));
            } else {
                saturated.add(extended);
                if (extended.body().countLiterals() > candidate.body().countLiterals()) {
                    stats.hypothesesExtended();
                }
            }
        }
        return new Pair<>(saturated, badRefinement);
    }

    /* old version, hardcoded here
    private HornClause saturationRefinement(HornClause candidate, List<Clause> constraints) {
        if (null == constraints || constraints.isEmpty()) {
            return candidate;
        }
        Pair<Clause, Map<Term, Term>> grounded = groundClause(candidate);
        Clause ground = grounded.r;
        Map<Term, Term> mapping = grounded.s;
        List<Clause> theory = ground.literals().stream().map(literal -> new Clause(literal.negation())).collect(Collectors.toList());
        theory.addAll(constraints);

        TheorySolver solver = new TheorySolver();
        solver.setSubsumptionMode(Matching.OI_SUBSUMPTION);
        Set<Literal> solved = solver.solve(theory);
        if (null == solved) {
            //System.out.println("theory solver returned null");
            stats.hypothesesKilled();
            return null;
        }

        Set<Literal> extended = solved.stream()
                .filter(literal -> TheorySimplifier.isGroundLiteralImplied(literal, theory))
                .map(literal -> reconstruct(literal, mapping))
                .collect(Collectors.toSet());
        extended.addAll(candidate.body().literals());

        if (extended.size() > candidate.body().literals().size()) {
            stats.hypothesesExtended();
        }

        return new HornClause(candidate.head(), new Clause(extended));
    }

    private Literal reconstruct(Literal literal, Map<Term, Term> mapping) {
        List<Term> terms = Arrays.asList(literal.arguments()).stream()
                .map(term -> mapping.get(term))
                .collect(Collectors.toList());
        return new Literal(literal.predicate(), terms);
    }

    private Pair<Clause, Map<Term, Term>> groundClause(HornClause candidate) {
        Map<Term, Term> substitution = new HashMap<>();
        Map<Term, Term> termToVariable = new HashMap<>();
        candidate.variables().forEach(variable -> {
            substitution.put(variable, Constant.construct("c" + substitution.size()));
            termToVariable.put(Constant.construct("c" + termToVariable.size()), variable);
        });
        Clause groundClause = LogicUtils.substitute(candidate.toClause(), substitution);
        return new Pair<>(groundClause, termToVariable);
    }
    */

    private SearchNodeInfo evaluate(HornClause horn, Set<Integer> learnFrom) {
        Pair<Set<Integer>, Set<Integer>> coverages = dataset.crispClassification(horn, learnFrom);
        stats.nodeExpanded();
        stats.addLength(horn.body().literals().size());
        return new SearchNodeInfo(coverages.r, coverages.s, horn, computeAccuracy(coverages.r, coverages.s, horn), computeAllowablitiy(coverages.r, coverages.s, horn), learnFrom);
    }

    private List<HornClause> refinements(HornClause hc, MultiMap<HornClause, Literal> badRefinements, Set<Integer> exampleCoveredByParents) {
        Set<IsoClauseWrapper> set = new HashSet<IsoClauseWrapper>();
        for (Pair<String, Integer> predicate : allAllowedPredicates) {
            for (HornClause newHc : refinements(hc, predicate, badRefinements, exampleCoveredByParents)) {
                set.add(new IsoClauseWrapper(newHc.toClause()));
            }
        }
        List<HornClause> retVal = new ArrayList<HornClause>();
        for (IsoClauseWrapper icw : set) {
            retVal.add(new HornClause(icw.getOriginalClause()));
        }
        return retVal;
    }

    private List<HornClause> filterIsomorphic(Collection<HornClause> coll) {
        Set<IsoClauseWrapper> set = new HashSet<IsoClauseWrapper>();
        for (HornClause newHc : coll) {
            set.add(new IsoClauseWrapper(newHc.toClause()));
        }
        List<HornClause> retVal = new ArrayList<HornClause>();
        for (IsoClauseWrapper icw : set) {
            retVal.add(new HornClause(icw.getOriginalClause()));
        }
        return retVal;
    }

    private List<HornClause> refinements(HornClause hc, Pair<String, Integer> predicate, MultiMap<HornClause, Literal> badRefinements, Set<Integer> exampleCoveredByParents) {
        long m1 = System.currentTimeMillis();
        Map<IsoClauseWrapper, Literal> refinements = new HashMap<IsoClauseWrapper, Literal>();
        Set<Variable> variables = hc.variables();
        Set<Variable> freshVariables = LogicUtils.freshVariables(variables, predicate.s);
        Literal freshLiteral = LogicUtils.newLiteral(predicate.r, predicate.s, freshVariables).negation();
        Clause originalClause = hc.toClause();
        Clause init = new Clause(Sugar.union(originalClause.literals(), freshLiteral));
        refinements.put(new IsoClauseWrapper(init), freshLiteral);

        for (int i = 0; i < predicate.s; i++) {
            Map<IsoClauseWrapper, Literal> newRefinements = new HashMap<IsoClauseWrapper, Literal>();
            for (Map.Entry<IsoClauseWrapper, Literal> entry : refinements.entrySet()) {
                Variable x = (Variable) entry.getValue().get(i);
                for (Variable v : entry.getKey().getOriginalClause().variables()) {
                    if (v != x) {
                        Clause substituted = LogicUtils.substitute(entry.getKey().getOriginalClause(), x, v);
                        Literal newLiteral = LogicUtils.substitute(entry.getValue(), x, v);
                        if (substituted.countLiterals() > originalClause.countLiterals() && !badRefinements.get(hc).contains(newLiteral) &&
                                !substituted.containsLiteral(newLiteral.negation())) {
                            HornClause candidate = new HornClause(substituted);
                            if (exampleCoveredByParents.size() >= minSupport
                                    && (dataset.numExistentialMatches(candidate, minSupport, exampleCoveredByParents) >= minSupport)) {
                                Clause candClause = candidate.toClause();
                                newRefinements.put(new IsoClauseWrapper(candClause), newLiteral);
                            } else {
                                badRefinements.put(hc, newLiteral);
                            }
                        } else {
                            //System.out.println("bad: "+newLiteral+" for "+hc);
                        }
                    }
                }
            }
            refinements.putAll(newRefinements);
        }

        Set<IsoClauseWrapper> refinementSet = refinements.keySet();
        List<HornClause> retVal = new ArrayList<HornClause>();
        for (IsoClauseWrapper icw : refinementSet) {
            if ((!this.connectedOnly || icw.getOriginalClause().connectedComponents().size() == 1)) {
                retVal.add(new HornClause(icw.getOriginalClause()));
            }
        }
        long m2 = System.currentTimeMillis();
        //System.out.println((m2-m1)+"ms");
        return retVal;
    }


    public void setDataset(MEDataset dataset) {
        this.dataset = dataset;
    }


    public static void main(String[] args) {

        HornClause hc = new HornClause(null, Clause.parse("a(X),b(X),c(X,Y)"));

        List<Clause> constraints = Sugar.list(Clause.parse("c(X,Y),!c(Y,X)")
                , Clause.parse("!c(X,Y),a(X)")
                //, Clause.parse("!a(X),!b(X)") // should be inconsistent with this line uncommented
        );

        List<Clause> data = Sugar.list(Clause.parse("a(a),b(b),c(a,b)"));
        List<Double> targets = Sugar.list(1.0);
        MEDataset dataset = new MEDataset(data, targets);
        SimpleLearner learner = new SimpleLearner(dataset,
                constraints, IntStream.range(0, dataset.getExamples().size()).mapToObj(i -> i).collect(Collectors.toSet()),
                new Accuracy(),
                new TimeDog(Long.MAX_VALUE),
                new MutableStats(),
                1);

        //HornClause res = learner.saturationRefinement(hc, constraints);
        HornClause res = (new RuleSaturator(constraints,Matching.OI_SUBSUMPTION)).saturate(hc);
        System.out.println(res);
    }
}