///*
// * Copyright (c) 2015 Ondrej Kuzelka
// *
// * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
// *
// * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
// *
// * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
// */
//
//package logicStuff.learning.constraints.shortConstraintLearner.developments.versions;
//
//import ida.ilp.logic.*;
//import ida.ilp.logic.special.IsoClauseWrapper;
//import ida.ilp.logic.subsumption.Matching;
//import ida.utils.Sugar;
//import ida.utils.tuples.Pair;
//import logicStuff.learning.datasets.Dataset;
//import logicStuff.learning.constraints.ShortConstraintLearner;
//import logicStuff.theories.TheorySimplifier;
//
//import java.util.*;
//
//
///**
// * Created by ondrejkuzelka on 01/02/17.
// */
//
//public class MultiDatasetShortConstraintLearnerCachingAllMinimalOnParents {
//
//    private int maxVariables = 5;
//
//    private int maxLength = 2;
//
//    private List<Dataset> datasets;
//
//    private Set<Pair<String, Integer>> allPredicates;
//
//    private long matchesQueried;
//    private long matchesAll;
//
//    public MultiDatasetShortConstraintLearnerCachingAllMinimalOnParents(List<Dataset> datasets, int maxLength, int maxVariables) {
//        this.datasets = datasets;
//        this.allPredicates = this.datasets.stream()
//                .map(dataset -> dataset.allPredicates())
//                .collect(HashSet::new, HashSet::addAll, HashSet::addAll);
//        this.maxLength = maxLength;
//        this.maxVariables = maxVariables;
//    }
//
//    /**
//     * The learned constraints are assumed to have an implicit alldiff on all variables but this alldiff is not added
//     * explicitly! To correctly use this type of rules with the TheorySolver, use theorySolver.setSubsumptionMode(Matching.OI_SUBSUMPTION).
//     *
//     * @return
//     */
//    public Map<String, Long> learnConstraints() {
//        Map<IsoClauseWrapper, Set<Dataset>> cache = new HashMap<>();
//
//        long lcTime = System.nanoTime();
//        Map<String, Long> times = new HashMap<>();
//        Set<IsoClauseWrapper> constraints = new HashSet<IsoClauseWrapper>();
//        Set<IsoClauseWrapper> beam = new HashSet<IsoClauseWrapper>();
//        IsoClauseWrapper empty = new IsoClauseWrapper(new Clause());
//        beam.add(empty);
//        addAllToCache(empty, datasets, cache);
//
//        long minimal = 0l;
//        long matchesAny = 0l;
//        long refinement = 0l;
//        for (int i = 0; i < maxLength; i++) {
//            Map<IsoClauseWrapper, Set<Dataset>> newCache = new HashMap<>();
//            Set<IsoClauseWrapper> newBeam = new HashSet<IsoClauseWrapper>();
//            for (IsoClauseWrapper c : beam) {
//                // closed list tady asi potreba neni, nebo jo? mel by to za to delat ten ICW
//                long refinementsCurrent = System.nanoTime();
//                List<Clause> refinements = refinements(c.getOriginalClause());
//                refinement += System.nanoTime() - refinementsCurrent;
//                for (Clause cand : refinements) {
//                    long matchesCurrent = System.nanoTime();
//                    Pair<Boolean, List<Dataset>> pair = matchesAny(cand, c, cache);
//                    boolean matchesCondition = !pair.r;
//                    matchesAny += System.nanoTime() - matchesCurrent;
//                    if (matchesCondition) {
//                        long minimalCurrent = System.nanoTime();
//                        boolean minimalCondition = minimal(cand);
//                        minimal += System.nanoTime() - minimalCurrent;
//                        if (minimalCondition) {
//                            constraints.add(new IsoClauseWrapper(LogicUtils.flipSigns(cand)));
//                        }
//                    } else if (cand.countLiterals() < maxLength) {
//                        IsoClauseWrapper canonic = new IsoClauseWrapper(cand);
//                        newBeam.add(canonic);
//                        Set<Dataset> removed = new HashSet<>(cache.get(c));
//                        removed.removeAll(pair.s);
//                        intersectWithCache(canonic, removed, newCache);
//                    } else if (cand.countLiterals() != maxVariables) {
//                        System.out.println("posledni vrstva?" + cand.countLiterals() + i + " " + " " + maxLength);
//                    }
//                }
//            }
//            beam = newBeam;
//            cache = newCache;
//            //System.out.println("just for now");
//            //break;
//        }
//        int numVars = 0;
//        Set<Clause> retVal = new HashSet<Clause>();
//        for (IsoClauseWrapper icw : constraints) {
//            retVal.add(icw.getOriginalClause());
//            numVars = Math.max(numVars, icw.getOriginalClause().variables().size());
//        }
//        long theoryTime = System.nanoTime();
//        List<Clause> theory = TheorySimplifier.simplify(retVal, numVars + 1);
//        times.put("lcTime", (System.nanoTime() - lcTime) / 1000000);
//        times.put("theorySimplifier", (System.nanoTime() - theoryTime) / 1000000);
//        times.put("matchesAny", matchesAny / 1000000);
//        times.put("minimal", minimal / 1000000);
//        times.put("refinement", refinement / 1000000);
//
//
//        System.out.println("theory\t" + theory.size());
//        theory.forEach(System.out::println);
//        System.out.println("theory\t" + theory.size());
//        System.out.println("matches queried:\t\t" + matchesQueried);
//        System.out.println("matches all:    \t\t" + matchesAll);
//        return times;
////        return Sugar.listFromCollections(retVal);
//    }
//
//    /**
//     * If the key is not in cache, then all the datasets are added to the cache. Otherwise intersection of cache.get(key) and datasets is inserted into the cache.
//     * <p>
//     * Should help to prune number of subsumptions calls by gradually removing samples (Dataset) which were not theta-subsumed by predecessors of the clause (key). The idea is based on monotonicity (general to specific).
//     *
//     * @param key
//     * @param datasets
//     * @param cache
//     */
//    private void intersectWithCache(IsoClauseWrapper key, Collection<Dataset> datasets, Map<IsoClauseWrapper, Set<Dataset>> cache) {
//        if (!cache.containsKey(key)) {
//            addAllToCache(key, datasets, cache);
//        } else {
//            Set<Dataset> intersection = new HashSet<>(cache.get(key));
//            intersection.retainAll(datasets);
//            cache.put(key, intersection);
//        }
//    }
//
//    private void addAllToCache(IsoClauseWrapper key, Collection<Dataset> datasets, Map<IsoClauseWrapper, Set<Dataset>> cache) {
//        if (!cache.containsKey(key)) {
//            cache.put(key, new HashSet<>());
//        }
//        cache.get(key).addAll(datasets);
//    }
//
//    private Pair<Boolean, List<Dataset>> matchesAny(Clause cand, IsoClauseWrapper parent, Map<IsoClauseWrapper, Set<Dataset>> cache) {
//        Collection<Dataset> scount = datasets;
//        if (cache.containsKey(parent)) {
//            scount = cache.get(parent);
//        }
//        matchesAll += scount.size();
//
//        boolean foundSome = false;
//        List<Dataset> toRemove = Sugar.list();
//        for (Dataset dataset : scount) {
//            matchesQueried++;
//            if (dataset.matches(cand)) {
//                foundSome = true;
//            } else {
//                toRemove.add(dataset);
//            }
//        }
//        return new Pair<>(foundSome, toRemove);
//    }
//
//
//    private boolean minimal(Clause cand) {
//        for (Literal l : cand.literals()) {
//            boolean existentialFound = false;
//            Clause shorther = new Clause(Sugar.collectionDifference(cand.literals(), l));
//            for (Dataset dataset : datasets) {
//                if (dataset.matches(shorther)) {
//                    existentialFound = true;
//                    break;
//                }
//            }
//            if (!existentialFound) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private List<Clause> refinements(Clause clause) {
//        Set<IsoClauseWrapper> set = new HashSet<IsoClauseWrapper>();
//        for (Pair<String, Integer> predicate : allPredicates) {
//            for (Clause newClause : Sugar.union(refinements(clause, predicate, true), refinements(clause, predicate, false))) {
//                set.add(new IsoClauseWrapper(newClause));
//            }
//        }
//        List<Clause> retVal = new ArrayList<Clause>();
//        for (IsoClauseWrapper icw : set) {
//            retVal.add(icw.getOriginalClause());
//        }
//        return retVal;
//    }
//
//    private List<Clause> refinements(Clause clause, Pair<String, Integer> predicate, boolean negated) {
//        Map<IsoClauseWrapper, Literal> refinements = new HashMap<IsoClauseWrapper, Literal>();
//        Set<Variable> variables = clause.variables();
//        Set<Variable> freshVariables = LogicUtils.freshVariables(variables, predicate.s);
//        Literal freshLiteral = LogicUtils.newLiteral(predicate.r, predicate.s, freshVariables);
//        if (negated) {
//            freshLiteral = freshLiteral.negation();
//        }
//
//        Clause init = new Clause(Sugar.union(clause.literals(), freshLiteral));
//        refinements.put(new IsoClauseWrapper(init), freshLiteral);
//
//        for (int i = 0; i < predicate.s; i++) {
//            Map<IsoClauseWrapper, Literal> newRefinements = new HashMap<IsoClauseWrapper, Literal>();
//            for (Map.Entry<IsoClauseWrapper, Literal> entry : refinements.entrySet()) {
//                Variable x = (Variable) entry.getValue().get(i);
//                for (Variable v : entry.getKey().getOriginalClause().variables()) {
//                    if (v != x) {
//                        Clause substituted = LogicUtils.substitute(entry.getKey().getOriginalClause(), x, v);
//                        Literal newLiteral = LogicUtils.substitute(entry.getValue(), x, v);
//                        if (substituted.countLiterals() > clause.countLiterals() && !substituted.containsLiteral(newLiteral.negation())) {
//                            HornClause candidate = new HornClause(substituted);
//                            Clause candClause = candidate.toClause();
//                            newRefinements.put(new IsoClauseWrapper(candClause), newLiteral);
//                        }
//                    }
//                }
//            }
//            refinements.putAll(newRefinements);
//        }
//        Set<IsoClauseWrapper> refinementSet = refinements.keySet();
//        List<Clause> retVal = new ArrayList<Clause>();
//        for (IsoClauseWrapper icw : refinementSet) {
//            if (icw.getOriginalClause().variables().size() <= this.maxVariables) {
//                retVal.add(icw.getOriginalClause());
//            }
//        }
//        return retVal;
//    }
//
//    public static void main(String[] args) throws Exception {
//        //Clause db = Clause.parse("b(x3),b(x1),a(x4),a(x2)");
//        Clause db = Clause.parse("a(x1),a(x2),b(x3),b(x4),e(x1,x2),e(x2,x1),e(x4,x5)");
//        //Clause db = Clause.parse("a(x1),a(x2),b(x3),b(x4),e(x1,x2),e(x2,x1),e(x5,x4)");
//        ShortConstraintLearner url = new ShortConstraintLearner(new Dataset(db, Matching.OI_SUBSUMPTION));
//
//        List<Clause> rules = url.learnConstraints();
//        for (Clause c : rules) {
//            System.out.println(c);
//        }
//
//    }
//
//}
//
