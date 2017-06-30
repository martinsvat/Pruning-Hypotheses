package ida.searchPruning.search;

import ida.utils.Sugar;

import java.util.List;

/**
 * It's not thread-safe!
 *
 * Created by Admin on 09.05.2017.
 */
public class MutableStats {

    private long searchedNodes;
    private long prunedNodes;
    private final List<Long> lengths = Sugar.list();
    private long killedHypothesis;
    private long extendedHypothesis;


    public long getSearchedNodes() {
        return searchedNodes;
    }

    public long getPrunedNodes() {
        return prunedNodes;
    }

    public void nodeExpanded(){
        this.searchedNodes++;
    }

    public void nodePruned(){
        this.prunedNodes++;
    }

    public void nodesPruned(int i) {
        this.prunedNodes += i;
    }

    public List<Long> getLengths() {
        return this.lengths;
    }

    public void addLength(long hypothesesLength) {
        this.lengths.add(hypothesesLength);
    }

    public void hypothesesExtended() {
        this.extendedHypothesis++;
    }

    public void hypothesesKilled() {
        this.killedHypothesis++;
    }

    public long getKilledHypothesis() {
        return killedHypothesis;
    }

    public long getExtendedHypothesis() {
        return extendedHypothesis;
    }

}
