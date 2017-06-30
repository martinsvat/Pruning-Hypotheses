package ida.searchPruning.evaluation;

import ida.searchPruning.search.collections.SearchNodeInfo;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 09.05.2017.
 */
public class LevelStats {

    private final int depth;
    private final long overallTime;
    private final long searchTime;
    private final long constraintTime;
    private final HashSet memory;
    private final long totalSearchedNodes;
    private final long totalKilledHypothesis;
    private final long totalExtendedHypothesis;
    private final List<Long> lengths;
    private final long totalPrunedNodes;


    public LevelStats(Set<SearchNodeInfo> memory, long searchTime, int depth, List<Long> lengths, long totalExtendedHypothesis, long totalKilledHypothesis, long totalPrunedNodes, long totalSearchedNodes, long constraintTime) {
        this.memory = new HashSet<>(memory);
        this.searchTime = searchTime;
        this.overallTime = searchTime + constraintTime;
        this.constraintTime = constraintTime;
        this.depth = depth;
        this.lengths = lengths;
        this.totalExtendedHypothesis = totalExtendedHypothesis;
        this.totalKilledHypothesis = totalKilledHypothesis;
        this.totalPrunedNodes = totalPrunedNodes;
        this.totalSearchedNodes = totalSearchedNodes;
    }

    public static LevelStats create(Set<SearchNodeInfo> memory, long nanoSearchStart, int depth, List<Long> lengths, long totalExtendedHypothesis, long totalKilledHypothesis, long totalPrunedNodes, long totalSearchedNodes, long constraintTime) {
        return new LevelStats(memory, System.nanoTime() - nanoSearchStart, depth, new ArrayList<>(lengths), totalExtendedHypothesis, totalKilledHypothesis, totalPrunedNodes, totalSearchedNodes, constraintTime);
    }

    public int getDepth() {
        return depth;
    }

    public long getConstraintTime() {
        return constraintTime;
    }

    public long getSearchTime() {
        return searchTime;
    }


    public long getOverallTime() {
        return overallTime;
    }

    public HashSet getMemory() {
        return memory;
    }

    public long getTotalSearchedNodes() {
        return totalSearchedNodes;
    }

    public long getTotalKilledHypothesis() {
        return totalKilledHypothesis;
    }

    public long getTotalExtendedHypothesis() {
        return totalExtendedHypothesis;
    }

    public List<Long> getLengths() {
        return lengths;
    }

    public long getTotalPrunedNodes() {
        return totalPrunedNodes;
    }
}
