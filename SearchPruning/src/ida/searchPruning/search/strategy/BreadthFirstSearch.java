package ida.searchPruning.search.strategy;

import ida.ilp.logic.Clause;
import ida.searchPruning.evaluation.BreadthResults;
import ida.searchPruning.search.MutableStats;
import ida.searchPruning.search.SimpleLearner;
import ida.utils.TimeDog;
import ida.searchPruning.search.collections.SearchNodeInfo;
import logicStuff.learning.datasets.MEDataset;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Admin on 03.05.2017.
 */
public class BreadthFirstSearch {

    private final MEDataset dataset;
    private final TimeDog time;
    private final int minSupport;
    private List<Clause> constraints;
    private final long constraintTime;

    public BreadthFirstSearch(MEDataset dataset, List<Clause> constraints, TimeDog timer, int minSupport, long constraintTime) {
        this.dataset = dataset;
        this.constraints = constraints;
        this.time = timer;
        this.minSupport = minSupport;
        this.constraintTime = constraintTime;
    }


    public BreadthResults search(int maxDepth, long searchStart) {
        Set<Integer> learnFrom = IntStream.range(0, dataset.getExamples().size()).mapToObj(i -> i).collect(Collectors.toSet());

        MutableStats stats = new MutableStats();
        SimpleLearner ruleLearner = new SimpleLearner(dataset, constraints, learnFrom, null, time.fromNow(), stats, minSupport);
        BreadthResults all = ruleLearner.breadthFirstSearch(maxDepth, searchStart,constraintTime);

        for (int idx = 0; idx < all.depths(); idx++) {
            Set<SearchNodeInfo> features = all.getRules(idx);
            all.setReducedFeatures(idx, featureExtraction(features));
        }
        return all;
    }

    private Set<SearchNodeInfo> featureExtraction(Set<SearchNodeInfo> all) {
        Set<SearchNodeInfo> filtered = new HashSet<>();
        List<SearchNodeInfo> sorted = new ArrayList<>(all);
        Collections.sort(sorted, (o1, o2) -> Integer.compare(o1.getRule().toClause().literals().size(), o2.getRule().toClause().literals().size()));
        Set<Set<Integer>> memory = new HashSet<>();
        for (SearchNodeInfo info : sorted) {
            Set<Integer> covered = info.getCovered();
            if (!memory.contains(covered)) {
                memory.add(covered);
                filtered.add(info);
            }
        }
        return filtered;
    }

}
