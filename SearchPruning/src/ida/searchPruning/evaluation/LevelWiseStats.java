package ida.searchPruning.evaluation;

import ida.searchPruning.search.collections.SearchNodeInfo;
import ida.searchPruning.util.Utils;
import logicStuff.learning.datasets.MEDataset;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Admin on 09.05.2017.
 */
public class LevelWiseStats {


    private final MEDataset dataset;
    private final List<Set<SearchNodeInfo>> rules;
    private final List<Set<SearchNodeInfo>> reduced;
    private final List<Set<Integer>> testIndexes;
    private final List<Set<Integer>> trainIndexes;
    private final List<LevelStats> sameLevel;
    private final Integer depth;
    private long fullyComputed;

    public LevelWiseStats(MEDataset dataset, int depth) {
        this.dataset = dataset;
        this.rules = new ArrayList<>();
        this.reduced = new ArrayList<>();
        this.depth = depth;
        this.testIndexes = new ArrayList<>();
        this.trainIndexes = new ArrayList<>();
        this.sameLevel = new ArrayList<>();
    }

    public void incorporate(LevelStats current, Set<SearchNodeInfo> rules, Set<SearchNodeInfo> reduced, Set<Integer> train, Set<Integer> test) {
        assert current.getDepth() == this.depth;
        this.reduced.add(new HashSet<>(reduced));
        this.rules.add(new HashSet<>(rules));
        sameLevel.add(current);
        fullyComputed++;
        this.trainIndexes.add(train);
        this.testIndexes.add(test);

        /*
        private final int depth;
        private final long overallTime;
        private final HashSet memory;
        private final long totalSearchedNodes;
        private final long totalKilledHypothesis;
        private final long totalExtendedHypothesis;
        private final List<Long> lengths;
        private final long totalPrunedNodes;

        throw new NotImplementedException("recopy times, etc");
        /*

            lengths.add(current.getAverageLengthOfHypothesis());

            // similar features are already reduced
            Set<SearchNodeInfo> reduced = current.getT().r;
            Set<SearchNodeInfo> nonReduced = current.getT().s;
            List<SearchNodeInfo> hypothesis = new ArrayList<>(reduced);
            results.add(new Triple<>(reduced, current.getConstraintTime(), current.getSearchTime()));

            nonReducedHypotheses.add((long) nonReduced.size());
            reducedHypotheses.add((long) reduced.size());
            searchedNodes.add(current.getTotalSearchedNodes());
            prunedNodes.add(new Double(current.getTotalPrunedNodes()));

            totalExtended.add(current.getExtendedHypothesis());
            totalKilled.add(current.getKilledHypothesis());

            File fil = new File(folder.getAbsolutePath() + File.separator + idx + ".test");
            System.out.println(fil.exists());
            writeData(dataset, train, hypothesis, new PrintWriter(folder.getAbsolutePath() + File.separator + idx + ".train"));
            writeData(dataset, test, hypothesis, new PrintWriter(folder.getAbsolutePath() + File.separator + idx + ".test"));
            writeData(nonReduced, new PrintWriter(folder.getAbsolutePath() + File.separator + idx + ".all"));
            writeData(reduced, new PrintWriter(folder.getAbsolutePath() + File.separator + idx + ".reduced"));

            System.out.println("all (" + reduced.size() + " / " + nonReduced.size() + ") found hypothesis in " + (current.getConstraintTime() / 1000000) + " and " + (current.getSearchTime() / 1000000));

         */
    }

    public MEDataset getDataset() {
        return dataset;
    }

    public Set<SearchNodeInfo> rules(int foldLike) {
        return this.rules.get(foldLike);
    }

    public Set<SearchNodeInfo> reduced(int foldLike) {
        return this.reduced.get(foldLike);
    }

    public Set<Integer> testIndexes(int foldLike) {
        return this.testIndexes.get(foldLike);
    }

    public Set<Integer> trainIndexes(int foldLike) {
        return this.trainIndexes.get(foldLike);
    }

    public Integer getDepth() {
        return this.depth;
    }

    public double constraintTime() {
        return Utils.average(Utils.longToDouble(this.sameLevel.stream().map(levelStats -> levelStats.getConstraintTime()).collect(Collectors.toList())));
    }

    public double searchTime() {
        return Utils.average(Utils.longToDouble(this.sameLevel.stream().map(levelStats -> levelStats.getSearchTime()).collect(Collectors.toList())));
    }

    public double overallTime() {
        return Utils.average(Utils.longToDouble(this.sameLevel.stream().map(levelStats -> levelStats.getOverallTime()).collect(Collectors.toList())));
    }

    public Double avgHypothesesLength() {
        List<Double> all = new ArrayList<>();
        for (Set<SearchNodeInfo> rule : this.rules) {
            all.addAll(rule.stream().map(info -> new Double(info.getRule().body().literals().size())).collect(Collectors.toList()));
        }
        return Utils.average(all);
    }

    public Double devianceHypothesesLength() {
        List<Double> all = new ArrayList<>();
        for (Set<SearchNodeInfo> rule : this.rules) {
            all.addAll(rule.stream().map(info -> new Double(info.getRule().body().literals().size())).collect(Collectors.toList()));
        }
        return Utils.deviation(all);
    }

    public Double searchedNodes() {
        return Utils.average(Utils.longToDouble(this.sameLevel.stream().map(levelStats -> levelStats.getTotalSearchedNodes()).collect(Collectors.toList())));
    }

    public Double avgPrunedNodes() {
        return Utils.average(Utils.longToDouble(this.sameLevel.stream().map(levelStats -> levelStats.getTotalPrunedNodes()).collect(Collectors.toList())));
    }

    public Double nonReducedHypotheses() {
        return Utils.average(Utils.intToDouble(this.rules.stream().map(set -> set.size()).collect(Collectors.toList())));
    }

    public Double numberOfReduced() {
        return Utils.average(Utils.intToDouble(this.reduced.stream().map(set -> set.size()).collect(Collectors.toList())));
    }

    public Double avgKilled() {
        return Utils.average(Utils.longToDouble(this.sameLevel.stream().map(levelStats -> levelStats.getTotalKilledHypothesis()).collect(Collectors.toList())));
    }

    public Double avgExtended() {
        return Utils.average(Utils.longToDouble(this.sameLevel.stream().map(levelStats -> levelStats.getTotalExtendedHypothesis()).collect(Collectors.toList())));
    }

    public long fullyComputed() {
        return this.fullyComputed;
    }
}
