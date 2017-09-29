/*
 * Copyright (c) 2015 Ondrej Kuzelka
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package logicStuff.learning.constraints.shortConstraintLearner;

import ida.ilp.logic.*;
import ida.ilp.logic.special.IsoClauseWrapper;
import ida.ilp.logic.subsumption.Matching;
import ida.utils.Sugar;
import ida.utils.collections.MultiMap;
import ida.utils.tuples.Pair;
import logicStuff.learning.constraints.ShortConstraintLearner;
import logicStuff.learning.datasets.Dataset;
import logicStuff.learning.saturation.RuleSaturator;
import logicStuff.theories.TheorySimplifier;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by ondrejkuzelka on 01/02/17.
 * <p>
 * Version with caching, pruning hacks, extended by using saturation' pruning within the constraint learner (MS).
 * All samples are tested on theta-subsumption, tested clause is cached (so multiple testing of a clause should not occur),
 * and being minimal is computed by querying cached parents.
 */

public class EnhancedMultiDatasetShortConstraintLearner {

    private int maxVariables = 5;

    private int maxLiterals = 2;

    private List<Dataset> datasets;

    private Set<Pair<String, Integer>> allPredicates;


    public EnhancedMultiDatasetShortConstraintLearner(List<Dataset> datasets, int maxLiterals, int maxVariables) {
        this.datasets = datasets;
        this.allPredicates = this.datasets.stream()
                .map(dataset -> dataset.allPredicates())
                .collect(HashSet::new, HashSet::addAll, HashSet::addAll);
        this.maxLiterals = maxLiterals;
        this.maxVariables = maxVariables;
    }

    /**
     * The learned constraints are assumed to have an implicit alldiff on all variables but this alldiff is not added
     * explicitly! To correctly use this type of rules with the TheorySolver, use theorySolver.setSubsumptionMode(Matching.OI_SUBSUMPTION).
     *
     * @return
     */
    public List<Clause> learnConstraints() {
        System.out.println("zkontrolovat ze se ne/maze cache rodicu, prislo mi ze by to kvuli tomu nemuselo fungovat)" +
                "\n" +
                "snad ten pristup pouzivani saturaci po 'vrstvach' nic nerozbije, radsi to zkontrolovat");

        Map<IsoClauseWrapper, Set<Dataset>> cache = new HashMap<>();

        Set<IsoClauseWrapper> constraints = new HashSet<IsoClauseWrapper>();
        MultiMap<Integer, IsoClauseWrapper> levelWiseOpenList = new MultiMap<>();
        IsoClauseWrapper empty = new IsoClauseWrapper(new Clause());
        addToOpenList(empty, levelWiseOpenList);
        addAllToCache(empty, datasets, cache);

        Set<IsoClauseWrapper> processed = new HashSet<>();
        for (int currentLevel = 0; currentLevel < maxLiterals; currentLevel++) {
            List<Clause> currentConstraints = constraints.stream().map(IsoClauseWrapper::getOriginalClause).collect(Collectors.toList());
            for (IsoClauseWrapper openedClause : levelWiseOpenList.get(currentLevel)) {
                for (Clause cand : refinements(openedClause.getOriginalClause(),currentConstraints)) {
                    IsoClauseWrapper canonicCandidate = new IsoClauseWrapper(cand);
                    if (processed.contains(canonicCandidate)) {
                        continue;
                    }
                    Pair<Boolean, List<Dataset>> pair = matchesAny(cand, openedClause, cache);
                    boolean matchesCondition = !pair.r;
                    if (matchesCondition) {
                        /* to check that minimal can be implemented by parents caching, use 
                        boolean count = allParentsInCache(cand);
                        boolean min = minimal(cand);
                        boolean minimalCondition = count && min;
                        if (minimalCondition)...
                        */
                        if (allParentsInCache(cand, cache.keySet())) { // ok tak tady doufam, ze ten dotaz do setu bezi podle hash pocitani a potom OI subsumpci, jinak to asi nefunguje...
                            // a taky to mapovani na hash
                            constraints.add(new IsoClauseWrapper(LogicUtils.flipSigns(cand)));
                        }
                    } else if (cand.countLiterals() < maxLiterals) {
                        addToOpenList(canonicCandidate,levelWiseOpenList);
                        Set<Dataset> removed = new HashSet<>(cache.get(openedClause));
                        removed.removeAll(pair.s);
                        intersectWithCache(canonicCandidate, removed, cache);
                    } else if (cand.countLiterals() != maxLiterals) { // just check, should never occur
                        System.out.println("the last layer? " + cand.countLiterals() + " " + currentLevel + " " + maxLiterals);
                    }
                    processed.add(canonicCandidate);
                }
            }
            levelWiseOpenList.remove(currentLevel); // GC
            // for removing cache, one should use something like
            Iterator<IsoClauseWrapper> iterator = cache.keySet().iterator();
            while(iterator.hasNext()){
                IsoClauseWrapper icw = iterator.next();
                if(icw.getOriginalClause().countLiterals() < currentLevel){
                    iterator.remove();
                }
            }
            //System.out.println("level " + currentLevel);
        }
        int numVars = 0;
        Set<Clause> retVal = new HashSet<Clause>();
        for (IsoClauseWrapper icw : constraints) {
            retVal.add(icw.getOriginalClause());
            numVars = Math.max(numVars, icw.getOriginalClause().variables().size());
        }
        //System.out.println("theory size before simplification\t" + retVal.size());
        List<Clause> theory = TheorySimplifier.simplify(retVal, numVars + 1);
        //System.out.println("theory size after simplification\t" + theory.size());
        return theory;
    }


    private void addToOpenList(IsoClauseWrapper clause, MultiMap<Integer, IsoClauseWrapper> levelWiseOpenList) {
        levelWiseOpenList.put(clause.getOriginalClause().countLiterals(), clause);
    }

    // equivalent method to minimal by querying cached parents
    private boolean allParentsInCache(Clause cand, Set<IsoClauseWrapper> parentsCache) {
        for (Literal l : cand.literals()) {
            Clause shorther = new Clause(Sugar.collectionDifference(cand.literals(), l));
            if (!parentsCache.contains(new IsoClauseWrapper(shorther))) {
                return false;
            }
        }
        return true;
    }

    /**
     * If the key is not in cache, then all the datasets are added to the cache. Otherwise intersection of cache.get(key) and datasets is inserted into the cache.
     * <p>
     * This should help to prune number of subsumptions calls by gradually removing samples (Dataset) which were not theta-subsumed by predecessors of the clause (key). The idea is based on monotonicity (general to specific).
     *
     * @param key
     * @param datasets
     * @param cache
     */
    private void intersectWithCache(IsoClauseWrapper key, Collection<Dataset> datasets, Map<IsoClauseWrapper, Set<Dataset>> cache) {
        if (!cache.containsKey(key)) {
            addAllToCache(key, datasets, cache);
        } else {
            Set<Dataset> intersection = new HashSet<>(cache.get(key));
            intersection.retainAll(datasets);
            cache.put(key, intersection);
        }
    }

    private void addAllToCache(IsoClauseWrapper key, Collection<Dataset> datasets, Map<IsoClauseWrapper, Set<Dataset>> cache) {
        if (!cache.containsKey(key)) {
            cache.put(key, new HashSet<>());
        }
        cache.get(key).addAll(datasets);
    }


    /**
     * matchesAny with caching all
     * @param candidate
     * @param parent
     * @param cache
     * @return pair<b,l> where b is true iff there is at least one example (dataset) which is theta-subsumed by the candidate clause, false otherwise; and l is list of datasets which are subsumed by the given candidate clause
     */
    private Pair<Boolean, List<Dataset>> matchesAny(Clause candidate, IsoClauseWrapper parent, Map<IsoClauseWrapper, Set<Dataset>> cache) {
        Collection<Dataset> scount = datasets;
        if (cache.containsKey(parent)) {
            scount = cache.get(parent);
        }

        boolean foundSome = false;
        List<Dataset> toRemove = Sugar.list();
        for (Dataset dataset : scount) {
            if (dataset.matches(candidate)) {
                foundSome = true;
            } else {
                toRemove.add(dataset);
            }
        }

        return new Pair<>(foundSome, toRemove);
    }


    // former testing of minimal without caching
    private boolean minimal(Clause cand) {
        for (Literal l : cand.literals()) {
            boolean existentialFound = false;
            Clause shorther = new Clause(Sugar.collectionDifference(cand.literals(), l));
            for (Dataset dataset : datasets) {
                if (dataset.matches(shorther)) {
                    existentialFound = true;
                    break;
                }
            }
            if (!existentialFound) {
                return false;
            }
        }
        return true;
    }

    /**
     * returns saturated, non-equivalent refinements of the clause
     * @param clause
     * @param constraints
     * @return
     */
    private List<Clause> refinements(Clause clause, List<Clause> constraints) { // sem pridat informace naucene z constraints
        System.out.println("s vypnutymi saturacemi to funguje jako MultiDataset verze, nicmene s nimi to produkuje vice pravidel -- neni to tim, ze v kazdem kroku (urovni) se pouziva jina BDT???");
        RuleSaturator saturator = RuleSaturator.create(constraints,Matching.OI_SUBSUMPTION);
        //RuleSaturator saturator = RuleSaturator.create(Sugar.list(),Matching.OI_SUBSUMPTION);
        Set<IsoClauseWrapper> set = new HashSet<IsoClauseWrapper>();
        for (Pair<String, Integer> predicate : allPredicates) {
            for (Clause newClause : Sugar.union(refinements(clause, predicate, true), refinements(clause, predicate, false))) {
                Clause saturation = saturator.saturate(newClause);
                if(null == saturation){
                    // is this really what we want? do we want to prune it????????
                    continue;
                }
                set.add(new IsoClauseWrapper(saturation));
            }
        }
        // isomorphism checking
        List<Clause> retVal = new ArrayList<Clause>();
        for (IsoClauseWrapper icw : set) {
            retVal.add(icw.getOriginalClause());
        }
        return retVal;
    }

    private List<Clause> refinements(Clause clause, Pair<String, Integer> predicate, boolean negated) {
        Map<IsoClauseWrapper, Literal> refinements = new HashMap<IsoClauseWrapper, Literal>();
        Set<Variable> variables = clause.variables();
        Set<Variable> freshVariables = LogicUtils.freshVariables(variables, predicate.s);
        Literal freshLiteral = LogicUtils.newLiteral(predicate.r, predicate.s, freshVariables);
        if (negated) {
            freshLiteral = freshLiteral.negation();
        }

        Clause init = new Clause(Sugar.union(clause.literals(), freshLiteral));
        refinements.put(new IsoClauseWrapper(init), freshLiteral);

        for (int i = 0; i < predicate.s; i++) {
            Map<IsoClauseWrapper, Literal> newRefinements = new HashMap<IsoClauseWrapper, Literal>();
            for (Map.Entry<IsoClauseWrapper, Literal> entry : refinements.entrySet()) {
                Variable x = (Variable) entry.getValue().get(i);
                for (Variable v : entry.getKey().getOriginalClause().variables()) {
                    if (v != x) {
                        Clause substituted = LogicUtils.substitute(entry.getKey().getOriginalClause(), x, v);
                        Literal newLiteral = LogicUtils.substitute(entry.getValue(), x, v);
                        if (substituted.countLiterals() > clause.countLiterals() && !substituted.containsLiteral(newLiteral.negation())) {
                            HornClause candidate = new HornClause(substituted);
                            Clause candClause = candidate.toClause();
                            newRefinements.put(new IsoClauseWrapper(candClause), newLiteral);
                        }
                    }
                }
            }
            refinements.putAll(newRefinements);
        }
        Set<IsoClauseWrapper> refinementSet = refinements.keySet();
        List<Clause> retVal = new ArrayList<Clause>();
        for (IsoClauseWrapper icw : refinementSet) {
            if (icw.getOriginalClause().variables().size() <= this.maxVariables) {
                retVal.add(icw.getOriginalClause());
            }
        }
        return retVal;
    }

    public static void main(String[] args) throws Exception {
        //Clause db = Clause.parse("b(x3),b(x1),a(x4),a(x2)");
        Clause db = Clause.parse("a(x1),a(x2),b(x3),b(x4),e(x1,x2),e(x2,x1),e(x4,x5)");
        //Clause db = Clause.parse("a(x1),a(x2),b(x3),b(x4),e(x1,x2),e(x2,x1),e(x5,x4)");
        ShortConstraintLearner url = new ShortConstraintLearner(new Dataset(db, Matching.OI_SUBSUMPTION));

        List<Clause> rules = url.learnConstraints();
        for (Clause c : rules) {
            System.out.println(c);
        }

    }

}

