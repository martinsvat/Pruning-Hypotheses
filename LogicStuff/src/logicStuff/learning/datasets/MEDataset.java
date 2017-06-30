package logicStuff.learning.datasets;

import ida.ilp.logic.Clause;
import ida.ilp.logic.HornClause;
import ida.ilp.logic.Literal;
import ida.ilp.logic.LogicUtils;
import ida.ilp.logic.subsumption.Matching;
import ida.utils.MutableDouble;
import ida.utils.Sugar;
import ida.utils.VectorUtils;
import ida.utils.tuples.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by martin.svatos on 27. 6. 2017.
 */
public class MEDataset implements DatasetInterface {
    private double[] targets;

    //one query per example, or no queries at all
    private List<Literal> queries;

    private List<Clause> examples;

    private Set<Pair<String, Integer>> allPredicates;

    private Set<Pair<String, Integer>> queryPredicates = new HashSet<Pair<String, Integer>>();

    private Matching matching;

    public MEDataset(List<Clause> example, List<Double> target) {
        this.targets = VectorUtils.toDoubleArray(target);
        this.examples = example;
        this.matching = new Matching(examples);
        this.matching.setSubsumptionMode(Matching.OI_SUBSUMPTION);
        this.allPredicates = LogicUtils.predicates(examples);
    }

    public MEDataset(List<Clause> examples, List<Literal> queries, List<Double> targets) {
        this(examples, targets);
        this.queries = queries;
    }

    public Pair<Set<Integer>, Set<Integer>> crispClassification(HornClause hc, Set<Integer> learnFrom) {
        Set<Integer> posCovered = Sugar.set();
        Set<Integer> negCovered = Sugar.set();
        for (Integer idx : learnFrom) {
            boolean prediction = crispClassification(hc, idx);
            if (prediction) {
                if (targets[idx] > 0.5) {
                    posCovered.add(idx);
                } else {
                    negCovered.add(idx);
                }
            }
        }
        return new Pair<>(posCovered, negCovered);
    }

    public boolean crispClassification(HornClause rule, int dbIndex) {
        Clause unified = rule.body();
        if (unified != null) {
            /*final int maxExhaustive = 1;
            Pair<Term[], List<Term[]>> substitutions = matching.allSubstitutions(unified, dbIndex, maxExhaustive);
            if (substitutions.s.size() > 0) {
                return true;
            }*/
            if (matching.subsumption(unified, dbIndex)) {
                return true;
            }

        }
        return false;
    }


    @Override
    public int numExistentialMatches(HornClause hc, int maxNum, Set<Integer> checkOnly) {
        int retVal = 0;
        if (queries == null) {
            Clause c = LogicUtils.flipSigns(hc.toClause());
            for (int i = 0; i < examples.size(); i++) {
                if (!checkOnly.contains(i)) {
                    continue;
                }
                if (matching.subsumption(c, i)) {
                    retVal++;
                    if (retVal++ >= maxNum) {
                        break;
                    }
                }
            }
        } else {
            for (int i = 0; i < examples.size(); i++) {
                if (!checkOnly.contains(i)) {
                    continue;
                }
                Clause c = LogicUtils.flipSigns(LogicUtils.flipSigns(hc.unify(queries.get(i))));
                if (matching.subsumption(c, i)) {
                    retVal++;
                    if (retVal++ >= maxNum) {
                        break;
                    }
                }
            }
        }
        return retVal;
    }

    @Override
    public int numExistentialMatches(HornClause hc, int maxNum) {
        return numExistentialMatches(hc, maxNum, IntStream.range(0, (null == queries) ? examples.size() : queries.size()).mapToObj(i -> i).collect(Collectors.toSet()));
    }

    @Override
    public Set<Pair<String, Integer>> queryPredicates() {
        return queryPredicates;
    }

    @Override
    public Set<Pair<String, Integer>> allPredicates() {
        return allPredicates;
    }

    public List<Clause> getExamples() {
        return examples;
    }

    public Set<Integer> getPosIdxs() {
        return IntStream.range(0, targets.length).filter(idx -> targets[idx] >= 0.5).mapToObj(idx -> idx).collect(Collectors.toSet());
    }

    public Set<Integer> getNegIdxs() {
        return IntStream.range(0, targets.length).filter(idx -> targets[idx] < 0.5).mapToObj(idx -> idx).collect(Collectors.toSet());
    }

    public boolean allNegativeExamples(Set<Integer> learnFrom) {
        for (Integer idx : learnFrom) {
            if (targets[idx] >= 0.5) {
                return false;
            }
        }
        return true;
    }

    public boolean isPredictionCorrect(List<HornClause> rules, Integer idx) {
        boolean prediction = false;

        for (HornClause rule : rules) {
            if (crispClassification(rule, idx)) {
                prediction = true;
                break;
            }
        }

        if (prediction && targets[idx] >= 0.5) {
            return true;
        } else if (!prediction && targets[idx] < 0.5) {
            return true;
        }
        return false;
    }

    public double[] getTargets() {
        return targets;
    }

}
