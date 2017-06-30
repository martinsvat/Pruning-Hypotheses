package ida.searchPruning.search.criterion;


import ida.ilp.logic.HornClause;

/**
 * Created by Admin on 03.05.2017.
 */
public class Accuracy implements Criterion {


    private final int maxNegCovered;

    public Accuracy() {
        this(0);
    }

    public Accuracy(int maxNegCovered) {
        this.maxNegCovered = maxNegCovered;
    }


    @Override
    public double compute(int posCovered, int negCovered, HornClause horn) {
        if(posCovered + negCovered < 1){
            return 0;
        }
        return (1.0 * posCovered) / (posCovered + negCovered);
    }

    @Override
    public boolean isAllowed(int posCovered, int negCovered, HornClause horn) {
        return negCovered <= maxNegCovered;
    }
}
