package ida.searchPruning.search.strategy;

import ida.searchPruning.evaluation.SearchStatsWrapper;

import java.util.Set;

/**
 * Created by Admin on 04.05.2017.
 */
@FunctionalInterface
public interface Searchable<T> {

    /**
     * returns SearchStatsWrapper
     * @param learnFrom
     * @return
     */
    public SearchStatsWrapper<T> searchTheory(Set<Integer> learnFrom);
}
