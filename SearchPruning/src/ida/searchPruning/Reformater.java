package ida.searchPruning;

import ida.ilp.logic.Clause;
import ida.ilp.logic.Literal;
import ida.ilp.logic.io.PseudoPrologParser;
import ida.utils.Sugar;
import ida.utils.tuples.Pair;
import ida.utils.tuples.Triple;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Support for transforming data from one notation to another.
 *
 * Created by martin.svatos on 04.05.2017.
 */
public class Reformater {

    public static Clause transform(Clause c) {
        List<Literal> literals = new ArrayList<Literal>();
        for (Literal l : c.literals()) {
            if (l.predicate().equals("bond")) {
                literals.add(new Literal(l.get(4).name() + "_bond", l.get(0), l.get(1)));
                literals.add(new Literal(l.get(2).name(), l.get(0)));
                literals.add(new Literal(l.get(3).name(), l.get(1)));
            }
        }
        return new Clause(literals);
    }


    public static void main(String[] args) throws IOException {
        //formate();
        count();
    }

    private static void count() throws IOException {
        String folder = "./in/nci/";
        File source = new File(folder);

        List<Triple<String, Long, Double>> datasets = Sugar.list();
        Arrays.asList(source.listFiles())
                .forEach(file -> {
                    long size = 0;
                    long posClass = 0;
                    try {
                        for (Pair<Clause, String> pair : PseudoPrologParser.read(new FileReader(folder + file.getName()))) {
                            if (pair.s.equals("+") || pair.s.equals("1.0")) {
                                posClass++;
                            }
                            size++;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    datasets.add(new Triple<String, Long, Double>(file.getName(), size, posClass * 1.0 / size));
                });
        Collections.sort(datasets, (o1, o2) -> Long.compare(o1.s, o2.s));
        datasets.forEach(System.out::println);
    }

    private static void formate() throws IOException {
        String folder = "./in/nci/";
        File source = new File(folder);
        for (File file : Arrays.asList(source.listFiles())) {
            Reader reader = new FileReader(folder + file.getName());

            StringBuilder sb = new StringBuilder();
            for (Pair<Clause, String> pair : PseudoPrologParser.read(reader)) {
                sb.append(pair.s + " " + transform(pair.r) + "\n");
            }

            PrintWriter writer = new PrintWriter("./in/nci_transformed/" + file.getName());
            writer.print(sb.toString());
            writer.close();
        }
    }

}
