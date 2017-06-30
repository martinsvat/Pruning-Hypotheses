package ida.searchPruning.search.strategy;

import ida.ilp.logic.Clause;
import ida.ilp.logic.HornClause;
import ida.searchPruning.evaluation.SearchStatsWrapper;
import ida.searchPruning.search.GeneralSearch;
import ida.searchPruning.search.SearchExpander;
import ida.searchPruning.search.SimpleLearner;
import ida.utils.TimeDog;
import ida.searchPruning.search.criterion.Criterion;
import logicStuff.learning.datasets.MEDataset;


import java.util.List;

/**
 * Created by Admin on 03.05.2017.
 */
public class BestFirstSearch {

    private final MEDataset dataset;
    private final Criterion criterion;
    private final List<Clause> constraints;
    private final TimeDog overallTime;
    private final TimeDog ruleLearningTime;
    private final int minSupport;

    public BestFirstSearch(MEDataset dataset, Criterion criterion, List<Clause> constraints, TimeDog overallTime, TimeDog ruleLearningTime, int minSupport) {
        this.dataset = dataset;
        this.criterion = criterion;
        this.constraints = constraints;
        this.overallTime = overallTime;
        this.ruleLearningTime = ruleLearningTime;
        this.minSupport = minSupport;
    }


    public SearchStatsWrapper<List<HornClause>> search(int maxNodes) {
        SearchExpander expander = (learnFrom, timeDog, stats) -> {
            SimpleLearner ruleLearner = new SimpleLearner(dataset, constraints, learnFrom, criterion, timeDog,stats, minSupport);
            return ruleLearner.bestFirstSearch(maxNodes,ruleLearningTime.fromNow());
        };
        GeneralSearch general = new GeneralSearch(dataset, expander, overallTime.fromNow());
        return general.search();
    }

}
