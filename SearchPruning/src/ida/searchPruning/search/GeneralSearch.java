package ida.searchPruning.search;

import ida.ilp.logic.HornClause;
import ida.searchPruning.evaluation.SearchStatsWrapper;
import ida.searchPruning.search.collections.SearchNodeInfo;
import ida.utils.Sugar;
import ida.utils.TimeDog;
import logicStuff.learning.datasets.MEDataset;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by martin.svatos on 29.04.2017.
 */
public class GeneralSearch {

    private final MEDataset dataset;
    private final SearchExpander expander;
    private final TimeDog overallTime;

    public GeneralSearch(MEDataset dataset, SearchExpander expander, TimeDog overallTime) {
        this.dataset = dataset;
        this.expander = expander;
        this.overallTime = overallTime;
    }

    public SearchStatsWrapper<List<HornClause>> search() {
        List<HornClause> theory = Sugar.list();
        Set<Integer> learnFrom = IntStream.range(0, dataset.getExamples().size()).mapToObj(i -> i).collect(Collectors.toSet());
        //TimeDog lastTime = overallTime.fromNow();
        MutableStats stats = new MutableStats();
        while (learnFrom.size() > 0 && !dataset.allNegativeExamples(learnFrom)) {
            //lastTime = overallTime.fromNow();
            SearchNodeInfo best = expander.learnRule(learnFrom, overallTime, stats); // change to overallTime.fromNow() only if you want to restrict overallTime for learning each hypothesis only
            if (null == best) {
                System.out.println("ending search since null was returned as a rule");
                break;
            }
            HornClause rule = best.getRule();
            String coveredAcc = best.getNumberOfCovered() == 0 ? "_" : (best.getAccuracy() + " (" + (best.getPositiveCoveredExamples().size() * 1.0 / (best.getNumberOfCovered())) + ")");
            System.out.println("learned rule (" + best.getCovered().size() + "/" + learnFrom.size() + ")\t" + coveredAcc + "\t" + rule);
            learnFrom.removeAll(best.getCovered());
            theory.add(rule);


            int pos = count(dataset, learnFrom, 1.0);
            int neg = count(dataset, learnFrom, 0.0);
            System.out.println("uncovered dataset distribution\t" + pos + " : " + neg);

            if (overallTime.isOut()) { // comment this if you want to restrict overallTime for learning each hypothesis only
                System.out.println("overallTime is out");
                break;
            }
        }
        double avgLengths = stats.getLengths().stream().mapToDouble(d -> d).average().orElse(0);
        //if(lastTime.isOut()){
        //    System.out.println("search ended since overallTime for one rule is up");
        //}
        return new SearchStatsWrapper<List<HornClause>>(theory, -1l, -1l, avgLengths, new Double(stats.getSearchedNodes()), stats.getPrunedNodes(), new Double(stats.getExtendedHypothesis()), new Double(stats.getKilledHypothesis()));
    }

    private int count(MEDataset dataset, Set<Integer> learnFrom, double target) {
        int count = 0;
        for (Integer idx : learnFrom) {
            if (Double.compare(dataset.getTargets()[idx], target) == 0) {
                count++;
            }
        }
        return count;
    }


}
