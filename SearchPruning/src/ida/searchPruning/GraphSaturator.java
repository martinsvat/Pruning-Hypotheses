package ida.searchPruning;

import ida.ilp.logic.Clause;
import ida.ilp.logic.subsumption.Matching;
import ida.utils.Sugar;
import logicStuff.learning.saturation.RuleSaturator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by martin.svatos on 26. 9. 2017.
 */
public class GraphSaturator {



    public static void main(String args[]){
        Clause example = Clause.parse(args[0]);
        List<Clause> theory = IntStream.range(1,args.length).mapToObj(i -> Clause.parse(args[i])).collect(Collectors.toList());
        RuleSaturator saturator = new RuleSaturator(theory, Matching.OI_SUBSUMPTION);
        //System.out.println(" takhle to asi nepujde :(");
        // ale jo, to asi pujde :)
        System.out.println(saturator.saturate(example).toString());
    }
}
