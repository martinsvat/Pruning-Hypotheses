package ida.searchPruning.search.criterion;


import ida.ilp.logic.HornClause;

/**
 * Created by Admin on 03.05.2017.
 */
public class Compression implements Criterion {

    private final int maxNegCovered;

    public Compression(int maxNegCovered) {
        this.maxNegCovered = maxNegCovered;
    }

    /**
     * Clause utility is P - N - L + 1, where P, N are the number of positive and negative examples covered by the clause, and L is the number of literals in the clause.
     * @param posCovered
     * @param negCovered
     * @param horn
     * @return
     */
    @Override
    public double compute(int posCovered, int negCovered, HornClause horn) {
        return posCovered - negCovered - horn.toClause().literals().size() + 1;
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
        return "Compression{" +
                "maxNegCovered=" + maxNegCovered +
                '}';
    }
}
