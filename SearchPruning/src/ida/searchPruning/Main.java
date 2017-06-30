package ida.searchPruning;

import ida.ilp.logic.Clause;
import ida.ilp.logic.Literal;

import java.io.*;
import java.util.*;

/**
 * Created by martin.svatos on 17.04.2017.
 */
public class Main {

    /**
     * arg1 -- path to dataset
     * arg2 -- type of search [breadth | beam | best]
     * arg3 -- maximal number of literals for constraint learner
     * arg4 -- maximal number of variables for constraint learner
     * arg5 -- max depth
     * arg6 -- query/rule minimal frequency (# of absolute occurrences)
     * arg7 -- number of folds for crossvalidation; optional
     *
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        args = new String[]{"./datasets/nci_transformed/gi50_screen_HOP_18.txt", "breadth" ,"3" ,"6" ,"2" ,"1" ,"0"};
        String dataPath = args[0];
        String method = args[1];
        int maxLength = Integer.valueOf(args[2]);
        int maxVariables = Integer.valueOf(args[3]);

        //System.out.println("add sleeep at start!!!!!!!!!!!!!");
        //Thread.sleep(2 * 60 * 1000);

        int maxDepth = 6;
        int minFrequency = 1;
        if (args.length >= 6) {
            maxDepth = Integer.valueOf(args[4]);
            minFrequency = Integer.valueOf(args[5]);
        }

        if (args.length >= 7) {
            int fold = Integer.valueOf(args[6]);
            Runner.run(dataPath, method, maxLength, maxVariables, maxDepth, minFrequency, fold);
        }else {
            Runner.run(dataPath, method, maxLength, maxVariables, maxDepth, minFrequency,-1);
        }
    }


}
