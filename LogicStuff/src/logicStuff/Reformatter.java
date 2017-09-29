package logicStuff;

import ida.ilp.logic.Clause;
import ida.ilp.logic.Constant;
import ida.ilp.logic.Literal;
import ida.ilp.logic.Term;
import ida.ilp.logic.io.PseudoPrologParser;
import ida.utils.Sugar;
import ida.utils.tuples.Pair;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by martin.svatos on 28. 9. 2017.
 */
public class Reformatter {

    public Reformatter() {
    }

    private List<Clause> load(String dataPath) throws IOException {
        Reader reader = new FileReader(dataPath);
        List<Clause> clauses = new ArrayList<Clause>();
        for (Pair<Clause, String> pair : PseudoPrologParser.read(reader)) {
            clauses.add(pair.r); // or transfer...
        }
        return clauses;
    }

    /**
     * instead of c=c-o notation uses graph notation with edges label as a vertex in the middle of the two edge's vertices
     * so, input of c=c-o produces c-bond2-c-bond1-o
     * <p>
     * for transformation into graph-like notation
     * <p>
     * most cases are unchecked (creating of new bonds predicates and so on)
     *
     * @param path
     * @return
     */
    public List<Clause> enumerateBonds(String path) throws IOException {
        List<Clause> clauses = load(path);
        Set<String> constants = clauses.stream()
                .map(clause -> clause.terms().stream().filter(term -> term instanceof Constant).map(term -> term.toString()).collect(Collectors.toSet()))
                .collect(() -> new HashSet<>(), Set::addAll, Set::addAll);
        ConstantFacotry factory = new ConstantFacotry(constants);
        return clauses.stream()
                .map(clause -> trasform(clause, factory))
                .collect(Collectors.toList());
    }

    private Clause trasform(Clause clause, ConstantFacotry factory) {
        Stream<String> stream = clause.literals().stream()
                .map(literal -> transformLiteral(literal, factory))
                .collect(() -> new HashSet<String>(), Set::addAll, Set::addAll)
                .stream();
        return Clause.parse(stream.collect(Collectors.joining(",")));
    }

    private Set<String> transformLiteral(Literal literal, ConstantFacotry factory) {
        if (literal.predicate().contains("bond")) {
            //resolve here
            assert literal.arity() == 2;
            Iterator<Term> iter = literal.terms().iterator();
            Term arg1 = iter.next();
            Term arg2 = iter.next();
            String bondConstant = factory.getFreshConstatnt();
            Set<String> set = Sugar.set();
            set.add("bond(" + arg1 + "," + bondConstant + ")");
            set.add("bond(" + bondConstant + "," + arg1 + ")");
            set.add("bond(" + arg2 + "," + bondConstant + ")");
            set.add("bond(" + bondConstant + "," + arg2 + ")");
            set.add(literal.predicate() + "(" + bondConstant + ")");
            return set;
        } else {
            return Sugar.set(literal.toString());
        }
    }

    class ConstantFacotry {
        private final Set<String> constants;

        public ConstantFacotry(Set<String> constants) {
            this.constants = constants;
        }

        public String getFreshConstatnt() {
            int idx = constants.size();
            while (true) {
                String constant = "c" + idx;
                if (constants.contains(constant)) {
                    idx++;
                    continue;
                }
                constants.add(constant);
                return constant;
            }
        }
    }

    public static Reformatter create() {
        return new Reformatter();
    }

    public static void main(String args[]) throws IOException {
        String path = "C:\\data\\school\\development\\graphMining\\graphMining\\data\\gi50_screen_786_0_partial.txt";
        Path file = Paths.get(path + "_reformatted_bonds");

        Reformatter reformatter = Reformatter.create();
        List<Clause> clauses = reformatter.enumerateBonds(path);

        clauses.forEach(System.out::println);
        Files.write(file, clauses.stream().map(Clause::toString).collect(Collectors.toList()), Charset.forName("UTF-8"));

    }

}
