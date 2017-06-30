package ida.searchPruning.search.criterion;


import ida.ilp.logic.HornClause;

/**
 * Created by Admin on 03.05.2017.
 */
public interface Criterion {

    double compute(int posCovered, int negCovered, HornClause horn);

    boolean isAllowed(int posCovered, int negCovered, HornClause horn);
}
