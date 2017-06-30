package ida.searchPruning.util;

import ida.ilp.logic.HornClause;
import ida.searchPruning.search.collections.SearchNodeInfo;
import logicStuff.learning.datasets.MEDataset;


import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Admin on 04.05.2017.
 */
public class Utils {
    private static final String separator = " ; ";

    public static void writeData(MEDataset dataset, Set<Integer> indices, List<SearchNodeInfo> hypotheses, PrintWriter printWriter) {
        StringBuilder sb = new StringBuilder();

        // first line
        hypotheses.forEach(hypothesis -> sb.append(separator).append(hypothesis.getRule()));
        sb.append(separator).append(" class ");
        indices.forEach(idx -> sb.append("\n" + toVector(dataset, idx, hypotheses)));

        printWriter.write(sb.toString());
        printWriter.close();
    }

    public static String toVector(MEDataset dataset, Integer exampleIdx, List<SearchNodeInfo> hypotheses) {
        StringBuilder sb = new StringBuilder();
        sb.append("example_" + exampleIdx);
        String holds = "1";
        String doesNotHold = "0";
        hypotheses.forEach(hypothesis -> sb.append(separator).append(dataset.crispClassification(hypothesis.getRule(), exampleIdx) ? holds : doesNotHold));
        sb.append(separator).append(Double.compare(1.0, dataset.getTargets()[exampleIdx]) == 0 ? "1" : "0");
        return sb.toString();
    }

    public static Double computeAccuracy(MEDataset dataset, List<HornClause> rules, Set<Integer> selectedExamples) {
        long rightPrediction = 0;

        for (Integer idx : selectedExamples) {
            if (dataset.isPredictionCorrect(rules, idx)) {
                rightPrediction++;
            }
        }
        return rightPrediction * 1.0 / selectedExamples.size();
    }

    public static List<Double> longToDouble(List<Long> l){
        return l.stream().map(i -> new Double(i)).collect(Collectors.toList());
    }

    public static List<Double> intToDouble(List<Integer> l){
        return l.stream().map(i -> new Double(i)).collect(Collectors.toList());
    }

    public static Double average(List<Double> list){
        return list.stream().mapToDouble(d -> d).average().orElse(0);
    }

    public static Double deviation(List<Double> list) {
        double mean = average(list);
        return Math.sqrt(list.stream().mapToDouble(d -> Math.pow(d - mean,2)).average().orElse(0));
    }
}
