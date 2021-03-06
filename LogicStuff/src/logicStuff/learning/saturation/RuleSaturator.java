package logicStuff.learning.saturation;

import ida.ilp.logic.*;
import ida.ilp.logic.subsumption.Matching;
import ida.utils.tuples.Pair;
import logicStuff.theories.TheorySimplifier;
import logicStuff.theories.TheorySolver;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by martin.svatos on 9. 6. 2017.
 */
public class RuleSaturator implements Saturator<HornClause> {

    private final List<Clause> constraints;
    private final int subsumptionMode;

    public RuleSaturator(List<Clause> constraints, int subsumptionMode) {
        this.constraints = constraints;
        this.subsumptionMode = subsumptionMode;
    }


    // returns null if constraints are violated by the candidate clause
    public Clause saturate(Clause candidate) {
        if (null == constraints || constraints.isEmpty()) {
            return candidate;
        }
        Pair<Clause, Map<Term, Term>> grounded = groundClause(candidate);
        Clause ground = grounded.r;
        Map<Term, Term> mapping = grounded.s;
        List<Clause> theory = ground.literals().stream().map(literal -> new Clause(literal.negation())).collect(Collectors.toList());
        theory.addAll(constraints);

        TheorySolver solver = new TheorySolver();
        solver.setSubsumptionMode(subsumptionMode);
        Set<Literal> solved = solver.solve(theory);
        if (null == solved) {
            return null;
        }

        Set<Literal> extended = solved.stream()
                .filter(literal -> TheorySimplifier.isGroundLiteralImplied(literal, theory))
                .map(literal -> reconstruct(literal, mapping))
                .collect(Collectors.toSet());
        extended.addAll(candidate.literals());

        return new Clause(extended);
    }

    public HornClause saturate(HornClause candidate) {
        Clause negativeSaturation = saturate(candidate.toClause());
        if(null == negativeSaturation){
            return null;
        }
        return new HornClause(negativeSaturation);
    }

    // returns null if constraints are violated by the candidate clause
    /*public HornClause saturate(HornClause candidate) {
        if (null == constraints || constraints.isEmpty()) {
            return candidate;
        }
        Pair<Clause, Map<Term, Term>> grounded = groundClause(candidate);
        Clause ground = grounded.r;
        Map<Term, Term> mapping = grounded.s;
        List<Clause> theory = ground.literals().stream().map(literal -> new Clause(literal.negation())).collect(Collectors.toList());
        theory.addAll(constraints);

        TheorySolver solver = new TheorySolver();
        solver.setSubsumptionMode(subsumptionMode);
        Set<Literal> solved = solver.solve(theory);
        if (null == solved) {
            return null;
        }

        Set<Literal> extended = solved.stream()
                .filter(literal -> TheorySimplifier.isGroundLiteralImplied(literal, theory))
                .map(literal -> reconstruct(literal, mapping))
                .collect(Collectors.toSet());
        extended.addAll(candidate.body().literals());

        return new HornClause(candidate.head(), new Clause(extended));
    }*/

    // this is probably somewhere in utils
    private Literal reconstruct(Literal literal, Map<Term, Term> mapping) {
        List<Term> terms = Arrays.asList(literal.arguments()).stream()
                .map(term -> mapping.get(term))
                .collect(Collectors.toList());
        return new Literal(literal.predicate(),literal.isNegated(), terms);
    }

    // this is probably somewhere in utils
    private Pair<Clause, Map<Term, Term>> groundClause(Clause candidate) {
        Map<Term, Term> substitution = new HashMap<>();
        Map<Term, Term> termToVariable = new HashMap<>();
        candidate.variables().forEach(variable -> {
            Constant constant = Constant.construct("c" + termToVariable.size());
            substitution.put(variable, constant);
            termToVariable.put(constant, variable);
        });
        Clause groundClause = LogicUtils.substitute(candidate, substitution);
        return new Pair<>(groundClause, termToVariable);
    }

    public static RuleSaturator create(List<Clause> constraints, int subsumptionMode){
        return new RuleSaturator(constraints,subsumptionMode);
    }

    public void getImpliedLiterals(Clause example) {
        //naimplementovat, spojit se saturate metodou
        throw new NotImplementedException();
    }
}
