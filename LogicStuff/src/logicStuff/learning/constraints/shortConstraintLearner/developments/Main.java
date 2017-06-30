//package logicStuff.learning.constraints.shortConstraintLearner.developments;
//
//import ida.ilp.logic.Clause;
//import ida.ilp.logic.io.PseudoPrologParser;
//import ida.ilp.logic.subsumption.Matching;
//import logicStuff.learning.constraints.shortConstraintLearner.MultiDatasetShortConstraintLearnerFaster;
//import ida.utils.tuples.Pair;
//import logicStuff.learning.datasets.Dataset;
//import logicStuff.learning.datasets.MultiExampleDataset;
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.Reader;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.*;
//
///**
// * Created by martin.svatos on 17.04.2017.
// */
//public class Main {
//
//    public static void main(String[] args) throws IOException, InterruptedException {
//        String dataPath = args[0];
//        String method = args[1];
//        int maxLength = Integer.valueOf(args[2]);
//        int maxVariables = Integer.valueOf(args[3]);
//
//        int maxDepth = 6;
//        int minFrequency = 1;
//        if (args.length >= 6) {
//            maxDepth = Integer.valueOf(args[4]);
//            minFrequency = Integer.valueOf(args[5]);
//        }
//
//        Map<Dataset,String> datasetToExample = new HashMap<>();
//
//        long loadingDataset = System.nanoTime();
//        MultiExampleDataset med = loadDataset(dataPath);
//        loadingDataset = (System.nanoTime() - loadingDataset) / 1000000;
//        long sampleDataset = System.nanoTime();
//        List<Dataset> datasets = med.getExamples().stream()
//                .map(sample -> {
//                    Dataset dataset = new Dataset(sample, Matching.OI_SUBSUMPTION);
//
//                    datasetToExample.put(dataset,sample.toString());
//                    return dataset;
//                })
//                .collect(Collectors.toList());
//        sampleDataset = (System.nanoTime() - sampleDataset) / 1000000;
//
//        //MultiDatasetShortConstraintLearnerOriginal shortConstraintLearner = new MultiDatasetShortConstraintLearnerOriginal(datasets, maxLength, maxVariables);
//        //MultiDatasetShortConstraintLearnerCaching shortConstraintLearner = new MultiDatasetShortConstraintLearnerCaching(datasets, maxLength, maxVariables);
//        //MultiDatasetShortConstraintLearnerCachingAll shortConstraintLearner = new MultiDatasetShortConstraintLearnerCachingAll(datasets, maxLength, maxVariables);
//        //MultiDatasetShortConstraintLearnerCachingAllParentsMemorized shortConstraintLearner = new MultiDatasetShortConstraintLearnerCachingAllParentsMemorized(datasets, maxLength, maxVariables);
//        //MultiDatasetShortConstraintLearnerCachingAllParentsMemorized shortConstraintLearner = new MultiDatasetShortConstraintLearnerCachingAllParentsMemorized(datasets, maxLength, maxVariables);
//        //MultiDatasetShortConstraintLearnerCachingAllParentsQueried shortConstraintLearner = new MultiDatasetShortConstraintLearnerCachingAllParentsQueried(datasets, maxLength, maxVariables);
//        //MultiDatasetShortConstraintLearnerCachingAllParentsQueriedMemorizedDoubled shortConstraintLearner = new MultiDatasetShortConstraintLearnerCachingAllParentsQueriedMemorizedDoubled(datasets, maxLength, maxVariables);
//        MultiDatasetShortConstraintLearnerFaster shortConstraintLearner = new MultiDatasetShortConstraintLearnerFaster(datasets,maxLength,maxVariables);//,datasetToExample);
//
//
//        long shortConstraint = System.nanoTime();
//        //Map<String, Long> times = shortConstraintLearner.learnConstraints();
//        List<Clause> theory = shortConstraintLearner.learnConstraints();
//        System.out.println("theory found\t" + theory.size());
//        theory.forEach(System.out::println);
//
//        /*for testing purpose
//        shortConstraint = (System.nanoTime() - shortConstraint) / 1000000;
//
//        System.out.println("loading dataset:\t\t\t" + loadingDataset);
//        System.out.println("creating sample datasets:\t" + sampleDataset);
//        System.out.println("short constraint:\t\t\t" + times.get("lcTime")); // shortConstraint
//        System.out.println("refinements:\t\t\t\t" + times.get("refinement"));
//        System.out.println("matches any:\t\t\t\t" + times.get("matchesAny"));
//        System.out.println("minimal:\t\t\t\t\t" + times.get("minimal"));
//        System.out.println("theory simplifier:\t\t\t" + times.get("theorySimplifier"));
//        System.out.println("" + shortConstraintLearner.getClass());*/
//
//    }
//
//    private static MultiExampleDataset loadDataset(String dataPath) throws IOException {
//        Reader reader = new FileReader(dataPath);
//
//        List<Double> targets = new ArrayList<Double>();
//        List<Clause> clauses = new ArrayList<Clause>();
//        for (Pair<Clause, String> pair : PseudoPrologParser.read(reader)) {
//            clauses.add(pair.r); // or transfer...
//            if (pair.s.equals("+") || pair.s.equals("1.0")) {
//                targets.add(1.0);
//            } else {
//                targets.add(0.0);
//            }
//        }
//        if (clauses.isEmpty()) {
//            System.out.println("empty input data file");
//            System.exit(-111);
//        }
//        return new MultiExampleDataset(clauses, targets);
//    }
//
//}
