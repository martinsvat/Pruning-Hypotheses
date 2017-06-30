package ida.searchPruning.search.strategy;

import ida.ilp.logic.Clause;
import ida.ilp.logic.HornClause;
import ida.searchPruning.evaluation.SearchStatsWrapper;
import ida.searchPruning.search.GeneralSearch;
import ida.searchPruning.search.SearchExpander;
import ida.searchPruning.search.SimpleLearner;
import ida.utils.TimeDog;
import ida.searchPruning.search.criterion.Criterion;
import ida.searchPruning.search.collections.SearchNodeInfo;
import logicStuff.learning.datasets.MEDataset;

import java.util.List;

/**
 * Created by Admin on 03.05.2017.
 */
public class BeamSearch {

    private final MEDataset dataset;
    private final List<Clause> constraints;
    private final Criterion criterion;
    private final TimeDog overallTime;
    private final TimeDog ruleLearningTime;
    private final int minSupport;

    public BeamSearch(MEDataset dataset, List<Clause> constraints, Criterion criterion, TimeDog overallTime, TimeDog ruleLearningTime, int minSupport) {
        this.dataset = dataset;
        this.constraints = constraints;
        this.criterion = criterion;
        this.overallTime = overallTime;
        this.ruleLearningTime = ruleLearningTime;
        this.minSupport = minSupport;
    }


    public SearchStatsWrapper<List<HornClause>> search(int maxDepth, int beamWidth) {
        SearchExpander expander = (learnFrom, timeDog, stats) -> {
            SimpleLearner ruleLearner = new SimpleLearner(dataset, constraints, learnFrom, criterion, timeDog, stats, minSupport);
            SearchNodeInfo best = ruleLearner.beamSearch(maxDepth, beamWidth, ruleLearningTime.fromNow());
            return best;
        };
        GeneralSearch general = new GeneralSearch(dataset, expander, overallTime.fromNow());
        return general.search();
    }

}
