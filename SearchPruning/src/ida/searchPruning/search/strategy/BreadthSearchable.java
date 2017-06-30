package ida.searchPruning.search.strategy;

import ida.searchPruning.evaluation.BreadthResults;

import java.util.Set;

/**
 * Created by Admin on 09.05.2017.
 */
@FunctionalInterface
public interface BreadthSearchable {

    public BreadthResults searchTheory(Set<Integer> learnFrom);

}

