package ida.searchPruning.evaluation;

import ida.ilp.logic.HornClause;
import ida.utils.tuples.Triple;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Admin on 04.05.2017.
 */
public class Stats<T> {

    private final List<Triple<T,Long,Long>> results;
    private final List<Double> trainErrors;
    private final List<Double> testErrors;



    private final List<Double> prunedNodes; // isoClauseWrapper
    private Double nonReducedHypothese;
    private Double reduced;
    private final List<Double> searchedNodes;
    private final List<Double> avgHypothesesLength;
    private final List<Double> extendedHypothesis;
    private final List<Double> killedHypothesis;

    public Stats(List<Triple<T, Long, Long>> results, List<Double> trainErrors, List<Double> testErrors, List<Double> lengths, List<Double> searchedNodes, List<Double> prunedNodes,List<Double> extendedHypothesis,List<Double> killedHypothesis) {
        this.results = results;
        this.trainErrors = trainErrors;
        this.testErrors = testErrors;
        this.avgHypothesesLength = lengths;
        this.searchedNodes = searchedNodes;
        this.prunedNodes = prunedNodes;
        this.extendedHypothesis = extendedHypothesis;
        this.killedHypothesis = killedHypothesis;
    }

    public List<Triple<T, Long, Long>> getResults() {
        return results;
    }

    public List<Double> getTrainErrors() {
        return trainErrors;
    }

    public List<Double> getTestErrors() {
        return testErrors;
    }

    public Double testAccuracy(){
        return average(testErrors);
    }

    public Double trainAccuracy(){
        return average(trainErrors);
    }

    public Double constraintTime(){
        return average(results.stream().map(res -> new Double(res.s)).collect(Collectors.toList()));
    }

    public Double searchTime(){
        return average(results.stream().map(res -> new Double(res.t)).collect(Collectors.toList()));
    }

    public Double avgHypothesesLength(){
        return average(avgHypothesesLength);
    }

    public Double overallTime(){
        return average(results.stream().map(res -> new Double(res.s + res.t)).collect(Collectors.toList()));
    }

    private Double average(List<Double> list){
        return list.stream().mapToDouble(d -> d).average().orElse(0);
    }

    public Double nonReducedHypotheses() {
        return nonReducedHypothese;
    }

    public Double numberOfReduced() {
        return reduced;
    }

    public void setNonReducedHypothese(Double nonReducedHypothese) {
        this.nonReducedHypothese = nonReducedHypothese;
    }

    public void setReduced(Double reduced) {
        this.reduced = reduced;
    }

    public Double devianceHypothesesLength() {
        return deviation(avgHypothesesLength);
    }

    private Double deviation(List<Double> list) {
        double mean = average(list);
        return Math.sqrt(list.stream().mapToDouble(d -> Math.pow(d - mean,2)).average().orElse(0));
    }

    public Double searchedNodes() {
        return average(searchedNodes);
    }

    public List<Double> getPrunedNodes() {
        return prunedNodes;
    }

    public double avgPrunedNodes() {
        return average(prunedNodes);
    }

    public double avgKilled(){
        return average(killedHypothesis);
    }

    public double avgExtended(){
        return average(extendedHypothesis);
    }

    public double avgNumberOfRules() {
        if(results.size() < 1){
            return 0.0d;
        }
        try{
            List<List<HornClause>> tt = new ArrayList<>();
            for (Triple<T,Long,Long> triple: results){
                tt.add((List<HornClause>)triple.r);
            }
            return average(tt.stream().map(theory -> new Double(theory.size())).collect(Collectors.toList()));
        }catch(Exception e){
            return 0.0d;
        }
    }
}

