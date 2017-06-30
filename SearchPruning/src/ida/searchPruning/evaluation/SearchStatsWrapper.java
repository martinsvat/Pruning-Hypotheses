package ida.searchPruning.evaluation;

/**
 * Created by Admin on 09.05.2017.
 */
public class SearchStatsWrapper<T> {

    private final T t;
    private final Long constraintTime;
    private final Long searchTime;
    private final Double averageLengthOfHypothese;
    private final Double totalSearchedNodes;
    private final Long totalPrunedNodes;
    private final Double extendedHypothesis;
    private final Double killedHypothesis;

    public SearchStatsWrapper(T t, Long constraintTime, Long searchTime, Double averageLengthOfHypothese, Double totalSearchedNodes, Long totalPrunedNodes, Double extendedHypothesis, Double killedHypothesis) {
        this.t = t;
        this.constraintTime = constraintTime;
        this.searchTime = searchTime;
        this.averageLengthOfHypothese = averageLengthOfHypothese;
        this.totalSearchedNodes = totalSearchedNodes;
        this.totalPrunedNodes = totalPrunedNodes;
        this.extendedHypothesis = extendedHypothesis;
        this.killedHypothesis = killedHypothesis;
    }

    public T getT() {
        return t;
    }

    public Long getConstraintTime() {
        return constraintTime;
    }

    public Long getSearchTime() {
        return searchTime;
    }

    public Double getAverageLengthOfHypothesis() {
        return averageLengthOfHypothese;
    }

    public Double getTotalSearchedNodes() {
        return totalSearchedNodes;
    }

    public Long getTotalPrunedNodes() {
        return totalPrunedNodes;
    }


    public Double getKilledHypothesis() {
        return this.killedHypothesis;
    }

    public Double getExtendedHypothesis() {
        return this.extendedHypothesis;
    }
}
