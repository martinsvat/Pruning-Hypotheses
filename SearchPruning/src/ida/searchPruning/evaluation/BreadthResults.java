package ida.searchPruning.evaluation;

import ida.searchPruning.search.MutableStats;
import ida.searchPruning.search.collections.SearchNodeInfo;
import logicStuff.learning.datasets.MEDataset;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 09.05.2017.
 */
public class BreadthResults {

    private final List<LevelStats> results = new ArrayList<>();
    private final long nanoSearchStart;
    private Set<Integer> test;
    private MEDataset dataset;
    private Set<Integer> train;
    private int foldNumber;
    private final List<Set<SearchNodeInfo>> rules = new ArrayList<>();
    private final List<Set<SearchNodeInfo>> reducedFeatures = new ArrayList<>();

    public BreadthResults(long nanoSearchStart) {
        this.nanoSearchStart = nanoSearchStart;
    }

    public int depths() {
        return results.size();
    }

    public LevelStats getDepth(int idx) {
        return results.get(idx);
    }

    public void setSets(MEDataset dataset, Set<Integer> train, Set<Integer> test) {
        this.dataset = dataset;
        this.train = train;
        this.test = test;
    }

    public void setFoldNumber(int foldNumber) {
        this.foldNumber = foldNumber;
    }

    public Set<SearchNodeInfo> getRules(int idx) {
        return rules.get(idx);
    }

    public List<Set<SearchNodeInfo>> getRules(){
        return rules;
    }

    public void setReducedFeatures(int idx, Set<SearchNodeInfo> features) {
        assert features.size() +1 != idx;
        reducedFeatures.add(new HashSet<>(features));
    }

    public Set<SearchNodeInfo> getReducedFeatures(int idx){
        return reducedFeatures.get(idx);
    }

    public void join(int idx, Set<SearchNodeInfo> memory, MutableStats stats, long constraintTime) {
        assert idx == results.size();
        rules.add(memory);
        results.add(LevelStats.create(memory,nanoSearchStart,idx,
                stats.getLengths(),
                stats.getExtendedHypothesis(),
                stats.getKilledHypothesis(),
                stats.getPrunedNodes(),
                stats.getSearchedNodes(),
                constraintTime));
    }

}
