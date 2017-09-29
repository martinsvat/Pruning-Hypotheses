package ida.searchPruning.util;

import ida.ilp.logic.Clause;
import ida.ilp.logic.io.PseudoPrologParser;
import ida.ilp.logic.special.IsoClauseWrapper;
import ida.ilp.logic.subsumption.Matching;
import ida.utils.tuples.Pair;
import logicStuff.learning.constraints.shortConstraintLearner.EnhancedMultiDatasetShortConstraintLearner;
import logicStuff.learning.constraints.shortConstraintLearner.MultiDatasetShortConstraintLearnerFaster;
import logicStuff.learning.datasets.Dataset;
import logicStuff.learning.datasets.MEDataset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by martin.svatos on 11. 9. 2017.
 */
public class SCLTesting {

    public static void main(String args[]) throws IOException {
        //comparingSCLandESCL();
        //debugSCL();
        debugSubsumption();
    }

    private static void debugSubsumption() throws IOException {
        String dataPath = "C:\\data\\school\\development\\pruningHypothesis\\logs\\dataset.txt";
        MEDataset med = loadDataset(dataPath);

        List<Dataset> datasets = IntStream.range(0, med.getExamples().size())
                .mapToObj(idx -> new Dataset(med.getExamples().get(idx), Matching.OI_SUBSUMPTION))
                .collect(Collectors.toList());

        Dataset dataset = datasets.get(0);
        for (int i = 0; i < 1000000000; i++) {
            System.out.println("volani\t" + i);
            try (Stream<String> stream = Files.lines(Paths.get("C:\\data\\school\\development\\pruningHypothesis\\logs\\partial.txt"))) {
                stream.forEach(line -> {
                    Clause clause = Clause.parse(line);
                    System.out.println(dataset.matches(clause));
                });
            }
        }
    }


    private static void debugSCL() throws IOException {
        String dataPath = "C:\\data\\school\\development\\pruningHypothesis\\datasets\\nci_transformed\\gi50_screen_KM20L2.txt";
        MEDataset med = loadDataset(dataPath);

        List<Dataset> datasets = IntStream.range(0, med.getExamples().size())
                .mapToObj(idx -> new Dataset(med.getExamples().get(idx), Matching.OI_SUBSUMPTION))
                .collect(Collectors.toList());

        int maxLength = 3;
        int maxVariables = maxLength * 2;

        System.out.println("starting scl");

        long sclSart = System.nanoTime();
        MultiDatasetShortConstraintLearnerFaster scl = new MultiDatasetShortConstraintLearnerFaster(datasets, maxLength, maxVariables);
        List<Clause> constraints = scl.learnConstraints();
        long sclEnd = System.nanoTime();

        System.out.println("starting enh");

    }


    private static void comparingSCLandESCL() throws IOException {
        String dataPath = "C:\\data\\school\\development\\pruningHypothesis\\datasets\\nci_transformed\\gi50_screen_KM20L2.txt";
        MEDataset med = loadDataset(dataPath);

        List<Dataset> datasets = IntStream.range(0, med.getExamples().size())
                .mapToObj(idx -> new Dataset(med.getExamples().get(idx), Matching.OI_SUBSUMPTION))
                .collect(Collectors.toList());

        int maxLength = 2;
        int maxVariables = maxLength * 2;

        System.out.println("starting scl");

        long sclSart = System.nanoTime();
        MultiDatasetShortConstraintLearnerFaster scl = new MultiDatasetShortConstraintLearnerFaster(datasets, maxLength, maxVariables);
        List<Clause> constraints = scl.learnConstraints();
        long sclEnd = System.nanoTime();

        System.out.println("starting enh");

        long sclExtendedSart = System.nanoTime();
        EnhancedMultiDatasetShortConstraintLearner enhanced = new EnhancedMultiDatasetShortConstraintLearner(datasets, maxLength, maxVariables);
        List<Clause> constraintsExtended = enhanced.learnConstraints();
        long sclExtendedEnd = System.nanoTime();

        System.out.println("slc time\t\t" + (sclEnd - sclSart));
        System.out.println("enh time\t\t" + (sclExtendedEnd - sclExtendedSart));

        System.out.println(constraints.size() + "\tvs\t" + constraintsExtended.size());
        System.out.println("slc");
        constraints.forEach(System.out::println);
        System.out.println("\nenh");
        constraintsExtended.forEach(System.out::println);


        System.out.println("\n\ndiffs");

        List<IsoClauseWrapper> isoConstraints = constraints.stream().map(IsoClauseWrapper::new).collect(Collectors.toList());
        List<IsoClauseWrapper> isoEnhanced = constraintsExtended.stream().map(IsoClauseWrapper::new).collect(Collectors.toList());

        Set<IsoClauseWrapper> first = new HashSet<>(isoConstraints);
        Set<IsoClauseWrapper> second = new HashSet<>(isoEnhanced);
        System.out.println("again\t" + first.size() + "\t" + second.size());

        first.removeAll(isoEnhanced);
        second.removeAll(isoConstraints);

        System.out.println("in first over\t" + first.size());
        System.out.println("in second over\t" + second.size());

        System.out.println("first over");
        first.forEach(iso -> System.out.println(iso.getOriginalClause()));

        System.out.println("\nsecond over");
        second.forEach(iso -> System.out.println(iso.getOriginalClause()));

        System.out.println("vraci to jine veci, uz pred theorySimplifier");
    }


    private static MEDataset loadDataset(String dataPath) throws IOException {
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

}
