package ida.searchPruning;

import ida.ilp.logic.Clause;
import ida.ilp.logic.HornClause;
import ida.ilp.logic.io.PseudoPrologParser;
import ida.ilp.logic.subsumption.Matching;
import ida.searchPruning.evaluation.*;
import ida.searchPruning.search.criterion.Coverage;
import ida.searchPruning.search.criterion.Criterion;
import ida.searchPruning.search.*;
import ida.searchPruning.search.collections.SearchNodeInfo;
import ida.searchPruning.search.strategy.*;
import ida.utils.TimeDog;
import logicStuff.learning.constraints.shortConstraintLearner.MultiDatasetShortConstraintLearnerFaster;
import ida.searchPruning.util.Utils;
import ida.utils.tuples.Pair;
import logicStuff.learning.datasets.Dataset;
import logicStuff.learning.datasets.MEDataset;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Admin on 04.05.2017.
 */
public class Runner {

    // in nanoseconds
    private final static long OVERALL_LIMIT = 2 * 60 * 60 * 1000000000l;
    private final static long RULE_LEARNING_LIMIT = 2 * 60 * 1000000000l;

    // setting
    //int maxDepth = method == "beam" ? 8 : 6;
    private final static int BEAM_WIDTH = 20;
    private final static int MAX_NODES = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {

        String dataPath =
                "./in/nci_transformed/gi50_screen_SR.txt";
        //"./in/ptcmr/transformed.txt";
        int maxLength = 2;
        int maxVariables = 2;
                /*"./in/ptcmr/ptc_mr.txt"; int maxLength = 2; int variables=5;*/

        /** /
         maxLength = 0;
         maxVariables = 0;
         /**/

        int maxDepth = 3;
        int minFrequency = 10;

        run(dataPath, "breadth", maxLength, maxVariables, maxDepth, minFrequency, -1);
    }

    public static void run(String dataPath, String method, int maxLength, int maxVariables, int maxDepth, int minFrequency, int fold) throws IOException {
        Runner runner = new Runner();
        SimpleLearner.VERBOUSE = false;
        int maxNegCovered = 10;

        //Criterion criterion = new Compression(maxNegCovered);
        Criterion criterion = new Coverage(maxNegCovered);

        System.out.println("change time constraints");
        //35 namisto 2
        //10 *

        TimeDog overallTime = new TimeDog(OVERALL_LIMIT, false); // false -> zapnuty, true -> vypnuty timeDog
        //TimeDog overallTime = new TimeDog(30 * 60 * 1000000000l, "breadth" == method ? true : false); // false -> zapnuty, true -> vypnuty timeDog
        TimeDog ruleLearningTime = new TimeDog(RULE_LEARNING_LIMIT, "breadth" == method ? true : false); // false -> zapnuty, true -> vypnuty timeDog
        //TimeDog overallTime = new TimeDog(40 * 1000000000l, "breadth" == method ? true : false); // false -> zapnuty, true -> vypnuty timeDog
        //TimeDog ruleLearningTime = new TimeDog(20 * 1000000000l, "breadth" == method ? true : false); // false -> zapnuty, true -> vypnuty timeDog

        runner.run(dataPath, method, maxLength, maxVariables, criterion, overallTime, dataPath, maxNegCovered, ruleLearningTime, maxDepth, minFrequency, fold);
    }

    private void run(String dataPath, String method, int maxLength, int maxVariables, Criterion criterion, TimeDog overallTime, String path, int maxNegCovered, TimeDog ruleLearningTime, int maxDepth, int minFrequency, final int userFold) throws IOException {
        File outputDir = createFolder(path + File.separator + method + "_" + maxLength + "_" + maxVariables + "_" + MAX_NODES + "_" + maxDepth + "_" + BEAM_WIDTH + "_" + minFrequency);
        MEDataset med = loadDataset(dataPath);

        int folds = resolveFolds(userFold, med.getExamples().size()); // -1;//1;//10;

        System.out.println("setting for directory");
        System.out.println("criterion\t" + ("breadth".equals(method) ? "none because breadth is used" : criterion));
        System.out.println("output dir\t" + outputDir);
        System.out.println("method\t" + method);
        System.out.println("maxLength\t" + maxLength);
        System.out.println("maxVariables\t" + maxVariables);
        System.out.println("maxNegCovered\t" + maxNegCovered);
        System.out.println("maxNanoTime\t" + overallTime.getLimit());
        System.out.println("maxDepth\t" + maxDepth);
        System.out.println("BEAM_WIDTH\t" + BEAM_WIDTH);
        System.out.println("MAX_NODES\t" + MAX_NODES);
        System.out.println("folds\t" + folds);
        System.out.println("minimal frequency\t" + minFrequency);


        Searchable<List<HornClause>> search = null;
        if ("beam".equals(method)) {
            search = beamSearch(med, maxLength, maxVariables, maxDepth, BEAM_WIDTH, criterion, overallTime, ruleLearningTime, minFrequency);
        } else if ("best".equals(method)) {
            search = bestFirstSearch(med, maxLength, maxVariables, MAX_NODES, criterion, overallTime, ruleLearningTime, minFrequency);
        }

        if (null != search) {
            Crossvalidation cross = new Crossvalidation(med);
            Stats<List<HornClause>> results = cross.run(search, folds);

            String result = "value\nfolds: " + (results.getResults().size()) + "\n" +
                    "constraint time [ms]: " + (results.constraintTime() / 1000000) + "\n" +
                    "search time [ms]: " + (results.searchTime() / 1000000) + "\n" +
                    "overall time [ms]: " + (results.overallTime() / 1000000) + "\n" +
                    "train acc: " + results.trainAccuracy() + "\n" +
                    "test acc: " + (results.testAccuracy()) + "\n" +
                    "searched nodes: " + (results.searchedNodes()) + "\n" +
                    "pruned nodes: " + (results.avgPrunedNodes()) + "\n" +
                    "avg hypotheses length: " + (results.avgHypothesesLength()) + "\n" +
                    "deviance hypotheses length: " + (results.devianceHypothesesLength()) + "\n" +
                    "avg killed: " + (results.avgKilled()) + "\n" +
                    "avg extended: " + (results.avgExtended()) + "\n" +
                    "learned rules: " + (results.avgNumberOfRules());

            System.out.println(result);
            writeToStatsFile(result, outputDir, -1);

            StringBuilder sb = new StringBuilder();
            sb.append("\nmodels");
            for (int idx = 0; idx < folds; idx++) {
                sb.append("\nmodel " + idx);
                results.getResults().get(idx).r.forEach(horn -> sb.append("\n\t" + horn));
            }
            System.out.println(sb.toString());
            writeToModelsFile(sb.toString(), outputDir);
        }

        if ("breadth".equals(method)) {
            BreadthSearchable breadth = breadthFirstSearch(med, maxLength, maxVariables, maxDepth, overallTime, minFrequency);
            Crossvalidation cross = new Crossvalidation(med);
            List<LevelWiseStats> results = cross.runBreadth(breadth, folds, outputDir, userFold);

            for (LevelWiseStats result : results) {
                maxDepth = result.getDepth();
                outputDir = createFolder(makeOutputDirName(path, method, maxLength, maxVariables, MAX_NODES, maxDepth, BEAM_WIDTH, minFrequency));

                System.out.println("storing " + result.getDepth() + "-th level at " + outputDir);
                if ((userFold < 0 && result.fullyComputed() != folds) || (result.fullyComputed() != 1 && userFold > -1)) {
                    System.out.println("stopping since this layer is not full (not every fold was computed)");
                    continue;
                }

                String output = "value\nfolds: " + (result.fullyComputed()) + "\n"
                        + "depth : " + (result.getDepth()) + "\n"
                        + "constraint time [ms]: " + (result.constraintTime() / 1000000) + "\n"
                        + "search time [ms]: " + (result.searchTime() / 1000000) + "\n"
                        + "overall time [ms]: " + (result.overallTime() / 1000000) + "\n"
                        + "avg hypotheses length: " + (result.avgHypothesesLength()) + "\n"
                        + "deviance hypotheses length: " + (result.devianceHypothesesLength()) + "\n"
                        + "searched nodes: " + (result.searchedNodes()) + "\n"
                        + "pruned nodes: " + (result.avgPrunedNodes()) + "\n"
                        + "# hypotheses: " + (result.nonReducedHypotheses()) + "\n"
                        + "# reduced: " + (result.numberOfReduced()) + "\n"
                        + "avg killed: " + (result.avgKilled()) + "\n"
                        + "avg extended: " + (result.avgExtended());
                System.out.println(result);
                writeToStatsFile(output, outputDir, userFold);

                for (int foldLike = 0; foldLike < result.fullyComputed(); foldLike++) {
                    int subscribe = foldLike;
                    if (userFold >= 0) {
                        subscribe = userFold;
                    }
                    Utils.writeData(result.getDataset(), result.trainIndexes(foldLike), new ArrayList<>(result.reduced(foldLike)), new PrintWriter(outputDir.getAbsolutePath() + File.separator + subscribe + ".train"));
                    Utils.writeData(result.getDataset(), result.testIndexes(foldLike), new ArrayList<>(result.reduced(foldLike)), new PrintWriter(outputDir.getAbsolutePath() + File.separator + subscribe + ".test"));
                    writeData(result.rules(foldLike), new PrintWriter(outputDir.getAbsolutePath() + File.separator + subscribe + ".all"));
                    writeData(result.reduced(foldLike), new PrintWriter(outputDir.getAbsolutePath() + File.separator + subscribe + ".reduced"));
                }
            }
        } else if (null == search) {
            new IllegalStateException("do not know option '" + method + "'");
        }
    }

    private int resolveFolds(int userFold, int size) {
        if (userFold < 0) {
            return 1;
        } else if (size < userFold) { // one against all
            return size;
        }else if(0 == userFold){
            return 1;
        }
        return userFold;
    }

    private String makeOutputDirName(String path, String method, int maxLength, int maxVariables, int maxNodes, int maxDepth, int beamWidth, int minFrequency) {
        return path + File.separator + method + "_" + maxLength + "_" + maxVariables + "_" + maxNodes + "_" + maxDepth + "_" + beamWidth + "_" + minFrequency;
    }

    private void writeToModelsFile(String s, File outputDir) throws FileNotFoundException {
        writeTo(s, new PrintWriter(outputDir + File.separator + "models.txt"));
    }

    private void writeToStatsFile(String s, File outputDir, int fold) throws FileNotFoundException {
        String output = fold >= 0 ? outputDir + File.separator + "stats_" + fold + ".txt" : outputDir + File.separator + "stats.txt";
        writeTo(s, new PrintWriter(output));
    }

    private void writeTo(String s, PrintWriter printWriter) {
        printWriter.write(s);
        printWriter.close();
    }

    private MEDataset loadDataset(String dataPath) throws IOException {
        Reader reader = new FileReader(dataPath);

        List<Double> targets = new ArrayList<Double>();
        List<Clause> clauses = new ArrayList<Clause>();
        for (Pair<Clause, String> pair : PseudoPrologParser.read(reader)) {
            clauses.add(pair.r); // or transfer...
            if (pair.s.equals("+") || pair.s.equals("1.0")) {
                targets.add(1.0);
            } else {
                targets.add(0.0);
            }
        }
        if (clauses.isEmpty()) {
            System.out.println("empty input data file");
            System.exit(-111);
        }
        return new MEDataset(clauses, targets);
    }

    private Searchable<List<HornClause>> bestFirstSearch(MEDataset med, int maxLength, int maxVariables, int maxNodes, Criterion criterion, TimeDog time, TimeDog ruleLearningTime, int minFrequency) {
        return (learnFrom) -> {
            List<Dataset> datasets = learnFrom.stream()
                    .map(idx -> new Dataset(med.getExamples().get(idx), Matching.OI_SUBSUMPTION))
                    .collect(Collectors.toList());

            long constraintStart = System.nanoTime();
            MultiDatasetShortConstraintLearnerFaster shortConstraintLearner = new MultiDatasetShortConstraintLearnerFaster(datasets, maxLength, maxVariables);
            List<Clause> constraints = shortConstraintLearner.learnConstraints();
            long constraintEnd = System.nanoTime();

            System.out.println("constraints learned: " + constraints.size() + " \t with setting " + maxLength + " : " + maxVariables);
            /*constraints.forEach(c -> System.out.println("\t" + c));
            if (constraints.isEmpty()) {
                System.out.println("the set of constraints is empty");
            }*/

            long searchStart = System.nanoTime();
            BestFirstSearch search = new BestFirstSearch(med, criterion, constraints, time, ruleLearningTime, minFrequency);
            SearchStatsWrapper<List<HornClause>> result = search.search(maxNodes);
            long searchEnd = System.nanoTime();
            return new SearchStatsWrapper<>(result.getT(), constraintEnd - constraintStart, searchEnd - searchStart, result.getAverageLengthOfHypothesis(), result.getTotalSearchedNodes(), result.getTotalPrunedNodes(), result.getExtendedHypothesis(), result.getKilledHypothesis());
        };
    }

    private Searchable<List<HornClause>> beamSearch(MEDataset med, int maxLength, int maxVariables, int maxDepth, int beamWidth, Criterion criterion, TimeDog time, TimeDog ruleLearningTime, int minFrequency) {
        return (learnFrom) -> {
            List<Dataset> datasets = learnFrom.stream()
                    .map(idx -> new Dataset(med.getExamples().get(idx), Matching.OI_SUBSUMPTION))
                    .collect(Collectors.toList());

            long constraintStart = System.nanoTime();
            MultiDatasetShortConstraintLearnerFaster shortConstraintLearner = new MultiDatasetShortConstraintLearnerFaster(datasets, maxLength, maxVariables);
            List<Clause> constraints = shortConstraintLearner.learnConstraints();
            long constraintEnd = System.nanoTime();

            System.out.println("constraints learned: " + constraints.size() + " \t with setting " + maxLength + " : " + maxVariables);
            /*constraints.forEach(c -> System.out.println("\t" + c));
            if (constraints.isEmpty()) {
                System.out.println("the set of constraints is empty");
            }*/

            long searchStart = System.nanoTime();
            BeamSearch search = new BeamSearch(med, constraints, criterion, time.fromNow(), ruleLearningTime, minFrequency);
            SearchStatsWrapper<List<HornClause>> result = search.search(maxDepth, beamWidth);
            long searchEnd = System.nanoTime();
            return new SearchStatsWrapper<List<HornClause>>(result.getT(), constraintEnd - constraintStart, searchEnd - searchStart, result.getAverageLengthOfHypothesis(), result.getTotalSearchedNodes(), result.getTotalPrunedNodes(), result.getExtendedHypothesis(), result.getKilledHypothesis());
        };
    }

    private BreadthSearchable breadthFirstSearch(MEDataset med, int maxLength, int maxVariables, int maxDepth, TimeDog time, int minFrequency) {
        return (learnFrom) -> {
            List<Dataset> datasets = learnFrom.stream()
                    .map(idx -> new Dataset(med.getExamples().get(idx), Matching.OI_SUBSUMPTION))
                    .collect(Collectors.toList());

            long constraintStart = System.nanoTime();
            MultiDatasetShortConstraintLearnerFaster shortConstraintLearner = new MultiDatasetShortConstraintLearnerFaster(datasets, maxLength, maxVariables);
            //MultiDatasetShortConstraintLearnerCachingAllParentsQueried shortConstraintLearner = new MultiDatasetShortConstraintLearnerCachingAllParentsQueried(datasets, maxLength, maxVariables);
            List<Clause> constraints = shortConstraintLearner.learnConstraints();
            long constraintEnd = System.nanoTime();

            System.out.println("constraints learned: " + constraints.size() + " \t with setting " + maxLength + " : " + maxVariables);
            /*constraints.forEach(c -> System.out.println("\t" + c));
            if (constraints.isEmpty()) {
                System.out.println("the set of constraints is empty");
            }*/

            long searchStart = System.nanoTime();
            BreadthFirstSearch search = new BreadthFirstSearch(med, constraints, time.fromNow(), minFrequency, constraintEnd - constraintStart);
            BreadthResults result = search.search(maxDepth, searchStart);
            long searchEnd = System.nanoTime();

            System.out.println("end of time");
            return result;
        };
    }

    private void writeData(Set<SearchNodeInfo> hypotheses, PrintWriter printWriter) {
        StringBuilder sb = new StringBuilder();
        hypotheses.forEach(hypothesis -> sb.append("\n").append(hypothesis.getRule()));
        printWriter.write(sb.toString());
        printWriter.close();
    }

    private File createFolder(String path) {
        while (path.contains(".")) {
            path = path.replace('.', '_');
        }
        File file = new File(path);
        file.mkdirs();
        return file;
    }


}
