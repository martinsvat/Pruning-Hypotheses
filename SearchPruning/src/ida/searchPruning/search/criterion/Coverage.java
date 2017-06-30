package ida.searchPruning.search.criterion;


import ida.ilp.logic.HornClause;

/**
 * Created by Admin on 03.05.2017.
 */
public class Coverage implements Criterion {

    private final int maxNegCovered;

    public Coverage(int maxNegCovered) {
        this.maxNegCovered = maxNegCovered;
    }


    @Override
    public double compute(int posCovered, int negCovered, HornClause horn) {
        return posCovered - negCovered;
    }

    @Override
    public boolean isAllowed(int posCovered, int negCovered, HornClause horn) {
        if (posCovered < 1){
            return false;
        }
        return negCovered <= maxNegCovered;
    }

    @Override
    public String toString() {
        return "Coverage{" +
                "maxNegCovered=" + maxNegCovered +
                '}';
    }
}
