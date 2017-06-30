package ida.searchPruning.evaluation;

import ida.ilp.logic.HornClause;
import ida.searchPruning.search.collections.SearchNodeInfo;
import ida.searchPruning.search.strategy.Searchable;
import ida.searchPruning.search.strategy.BreadthSearchable;
import ida.searchPruning.util.Utils;
import ida.utils.Sugar;
import ida.utils.tuples.Pair;
import ida.utils.tuples.Triple;
import logicStuff.learning.datasets.MEDataset;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Admin on 04.05.2017.
 */
public class Crossvalidation {
    private final MEDataset dataset;


    private static final Random rnd = new Random(13);

    public Crossvalidation(MEDataset med) {
        this.dataset = med;
    }


    public Stats<List<HornClause>> run(Searchable<List<HornClause>> search, int folds) {
        List<Integer> indices = IntStream.range(0, dataset.getExamples().size()).mapToObj(i -> i).collect(Collectors.toList());
        Collections.shuffle(indices, rnd);

        //List<Quadruple<List<HornClause>, Long, Long,Double>> results = Sugar.list();
        List<Triple<List<HornClause>, Long, Long>> results = Sugar.list();
        List<Double> trainAccuracy = Sugar.list();
        List<Double> testAccuracy = Sugar.list();

        int step = indices.size() / folds;
        List<Double> lengths = Sugar.list();
        List<Double> searchedNodes = Sugar.list();
        List<Double> prunedNodes = Sugar.list();
        List<Double> totalExtended = Sugar.list();
        List<Double> totalKilled = Sugar.list();

        System.out.println("step is\t" + step);
        for (int idx = 0; idx < folds; idx++) {
            Pair<Set<Integer>, Set<Integer>> trainTest = split(idx, step, folds, indices);
            Set<Integer> train = trainTest.r;
            Set<Integer> test = trainTest.s;

            SearchStatsWrapper<List<HornClause>> current = search.searchTheory(train);

            trainAccuracy.add(Utils.computeAccuracy(dataset, current.getT(), train));
            testAccuracy.add(Utils.computeAccuracy(dataset, current.getT(), test));
            results.add(new Triple<>(current.getT(), current.getConstraintTime(), current.getSearchTime()));
            lengths.add(current.getAverageLengthOfHypothesis());
            searchedNodes.add(current.getTotalSearchedNodes());
            prunedNodes.add(new Double(current.getTotalPrunedNodes()));

            totalExtended.add(current.getExtendedHypothesis());
            totalKilled.add(current.getKilledHypothesis());

            System.out.println("\ttrain accuracy: " + trainAccuracy.get(trainAccuracy.size() - 1));
            System.out.println("\ttest accuracy: " + testAccuracy.get(testAccuracy.size() - 1));
        }
        return new Stats<List<HornClause>>(results, trainAccuracy, testAccuracy, lengths, searchedNodes, prunedNodes, totalExtended, totalKilled);
    }

    private Pair<Set<Integer>, Set<Integer>> split(int idx, int step, int folds, List<Integer> indices) {
        if (folds < 2){
            return new Pair<>(new HashSet<>(indices), new HashSet<>(indices));
        }

        int start = idx * step;
        System.out.println("starting " + (1 + idx) + "-th fold");
        int ceil = Math.min(start + step, indices.size());
        if (folds == step) {
            ceil = indices.size();
        }

        Set<Integer> test = 1 == folds ? new HashSet<>() : new HashSet<>(indices.subList(start, ceil));
        Set<Integer> train = new HashSet<>(indices);
        train.removeAll(test);
        return new Pair<>(train, test);
    }


    public List<LevelWiseStats> runBreadth(BreadthSearchable search, int folds, File folder,int fold) throws FileNotFoundException {
        List<Integer> indices = IntStream.range(0, dataset.getExamples().size()).mapToObj(i -> i).collect(Collectors.toList());
        Collections.shuffle(indices, rnd);

        int step = indices.size() / folds;
        System.out.println("step is\t" + step);

        List<LevelWiseStats> result = new ArrayList<>();

        for (int foldIdx = 0; foldIdx < folds; foldIdx++) {
            if (fold >= 0){
                foldIdx = fold;
            }
            Pair<Set<Integer>, Set<Integer>> trainTest = split(foldIdx, step, folds, indices);
            Set<Integer> train = trainTest.r;
            Set<Integer> test = trainTest.s;

            BreadthResults current = search.searchTheory(train);
            current.setSets(dataset, train, test);
            current.setFoldNumber(foldIdx);

            System.out.println("fold " + foldIdx + " has ended");
            mergeResults(result, current,train,test);
            if (fold >= 0){
                break;
            }
        }

        return result;

    }

    private void mergeResults(List<LevelWiseStats> result, BreadthResults current, Set<Integer> train, Set<Integer> test) {
        for (int depth = 0; depth < current.depths(); depth++) {
            if (result.size()- 1< depth) {
                result.add(new LevelWiseStats(dataset, depth));
            }
            addTo(current.getDepth(depth), current.getRules(depth),current.getReducedFeatures(depth),result.get(depth),train,test);
        }
    }

    private void addTo(LevelStats current, Set<SearchNodeInfo> rules, Set<SearchNodeInfo> reduced, LevelWiseStats box, Set<Integer> train, Set<Integer> test) {
        box.incorporate(current,rules, reduced,train,test);
    }


}
