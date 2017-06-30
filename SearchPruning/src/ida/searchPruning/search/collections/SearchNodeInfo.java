package ida.searchPruning.search.collections;

import ida.ilp.logic.HornClause;
import ida.ilp.logic.special.IsoClauseWrapper;
import ida.utils.Sugar;

import java.util.List;
import java.util.Set;


/**
 * Created by Admin on 03.05.2017.
 */
public class SearchNodeInfo implements Comparable {

    private final Set<Integer> positiveCoveredExamples;
    private final Set<Integer> negativeCoveredExamples;
    private final HornClause rule;
    private final double accuracy;
    private final Set<Integer> learnFrom;
    private final IsoClauseWrapper isoClauseWrapper;
    private final boolean isAllowed;
    private List<Long> lengths;

    public SearchNodeInfo(Set<Integer> positiveCoveredExamples, Set<Integer> negativeCoveredExamples, HornClause rule, double accuracy, boolean isAllowed, Set<Integer> learnFrom, List<Long> lengths) {
        this.positiveCoveredExamples = positiveCoveredExamples;
        this.negativeCoveredExamples = negativeCoveredExamples;
        this.rule = rule;
        this.accuracy = accuracy;
        this.learnFrom = learnFrom;
        this.isoClauseWrapper = new IsoClauseWrapper(rule.toClause());
        this.isAllowed = isAllowed;
        this.lengths = lengths;
    }

    public SearchNodeInfo(Set<Integer> pos, Set<Integer> neg, HornClause empty, double accuracy, boolean isAllowed, Set<Integer> learnFrom) {
        this(pos,neg,empty,accuracy,isAllowed,learnFrom,Sugar.list());
    }

    public IsoClauseWrapper getIsoClauseWrapper() {
        return isoClauseWrapper;
    }

    public boolean isAllowed() {
        return isAllowed;
    }

    public Set<Integer> getPositiveCoveredExamples() {
        return positiveCoveredExamples;
    }

    public Set<Integer> getNegativeCoveredExamples() {
        return negativeCoveredExamples;
    }

    public HornClause getRule() {
        return rule;
    }

    public double getAccuracy() {
        return accuracy;
    }

    /**
     * Returns set of idx it was tested on (the learnFrom of its first parent).
     * @return
     */
    public Set<Integer> getLearnFrom() {
        return learnFrom;
    }

    /**
     * Returns set of indices of covered examples (union of posCovered and negCovered).
     * @return
     */
    public Set<Integer> getCovered(){
        return Sugar.setFromCollections(negativeCoveredExamples,positiveCoveredExamples);
    }

    @Override
    public int compareTo(Object o) {
        SearchNodeInfo o2 = (SearchNodeInfo) o;
        int allowedComparison = Boolean.compare(isAllowed, o2.isAllowed());
        if(0 == allowedComparison){
            return -Double.compare(getAccuracy(),o2.getAccuracy());
        }
        return -allowedComparison;
    }

    @Override
    public String toString() {
        return "searchWrapper{" +
                "acc=" + accuracy +
                ", allowed=" + isAllowed +
                ", #pos=" + positiveCoveredExamples.size() +
                ", #neg=" + negativeCoveredExamples.size() +
                ", rule=" + rule +
                ", learnFrom=" + learnFrom.size() +
                ", ICW=" + this.isoClauseWrapper +'}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchNodeInfo that = (SearchNodeInfo) o;

        return isoClauseWrapper.equals(that.isoClauseWrapper);

    }

    @Override
    public int hashCode() {
        return isoClauseWrapper.hashCode();
    }

    public int getNumberOfCovered(){
        return positiveCoveredExamples.size() + negativeCoveredExamples.size();
    }

    public List<Long> getLengths() {
        return lengths;
    }

    public void setLengths(List<Long> lengths) {
        this.lengths = lengths;
    }


    /**
     * Updates subsumed examples by this clause. Given the search node, it updates this search node to contain only intersection of subsumed examples by these two search nodes; as each of the search node in fact represents a parent of the clause.
     * @param t
     * @param <T>
     */
    public <T extends SearchNodeInfo> void update(T t) {
        // System.out.println("this will probably never work, since it is a set of examples subsumed by parent"); // would be applicable in some other search strategies that we do not use (e.g. best successor search)
        // should not it take get covered instead???
        // yes, here should be learnFrom, since it is covered of parents
       this.learnFrom.retainAll(t.getLearnFrom());
    }
}
