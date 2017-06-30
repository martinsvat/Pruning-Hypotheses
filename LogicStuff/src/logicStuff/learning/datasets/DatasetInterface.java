package logicStuff.learning.datasets;

import ida.ilp.logic.HornClause;
import ida.ilp.logic.Literal;
import ida.utils.tuples.Pair;

import java.util.Map;
import java.util.Set;

/**
 * Created by martin.svatos on 27. 6. 2017.
 */
public interface DatasetInterface {

    public int numExistentialMatches(HornClause hc, int maxNum);

    public int numExistentialMatches(HornClause hc, int maxNum, Set<Integer> checkOnly);

    public Set<Pair<String, Integer>> queryPredicates();

    public Set<Pair<String, Integer>> allPredicates();

    public Pair<Set<Integer>, Set<Integer>> crispClassification(HornClause hc, Set<Integer> learnFrom);

    Set<Integer> getPosIdxs();

    Set<Integer> getNegIdxs();
}
