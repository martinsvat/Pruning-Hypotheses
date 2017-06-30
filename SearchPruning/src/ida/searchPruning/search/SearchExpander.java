package ida.searchPruning.search;


import ida.searchPruning.search.collections.SearchNodeInfo;
import ida.utils.TimeDog;

import java.util.Set;

/**
 * Created by Admin on 03.05.2017.
 */
@FunctionalInterface
public interface SearchExpander {

    public SearchNodeInfo learnRule(Set<Integer> learnFrom, TimeDog time, MutableStats stats);

}
