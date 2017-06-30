package ida.searchPruning.metavo;

import ida.utils.Sugar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Support for generation of scripts for experiments.
 *
 * Created by martin.svatos on 05.05.2017.
 */
public class ScriptGenerator {

    public static String place = "/auto/praha1/svatoma1/searchPruning/";
    public static String ending = "8foldWise";


    public static void main(String[] args) throws FileNotFoundException {
        List<String> domains = Arrays.asList(new String[]{
                //"gi50_screen_M19_MEL.txt",
                //"gi50_screen_HS_578T.txt",
                //"gi50_screen_NCI_ADR_RES.txt",
                //"gi50_screen_U251.txt"
                "gi50_screen_P388_ADR.txt",
                "gi50_screen_SN12K1.txt",
                "gi50_screen_RXF_631.txt",
                "gi50_screen_LXFL_529.txt",
                "gi50_screen_HOP_18.txt",
                "gi50_screen_XF_498.txt",
                "gi50_screen_SNB_78.txt",
                "gi50_screen_DMS_273.txt",
                "gi50_screen_KM20L2.txt",
                "gi50_screen_PC_3.txt",
                "gi50_screen_U251.txt"
        }).stream().collect(Collectors.toList());

        String outputDir = "./" + ending;

        File outputDirectory = new File(outputDir);
        outputDirectory.mkdirs();
        StringBuilder runner = new StringBuilder();

        int start = 0;
        for (String method : Sugar.list("breadth"//, "best","beam"//, "beam"//#, "breadth"
        )) {
            if ("breadth" == method) {
                for (int frequency : Sugar.list(1,20,50,100)) {
                    for (int fold = 0; fold < 10; fold++) {
                        start = printSetting(start, domains, outputDir, runner, method, 14, frequency, fold);
                    }
                }
            } else {
                start = printSetting(start, domains, outputDir, runner, method, 7, 1, -1);
            }
        }

        writeTo(runner.toString(), outputDir + File.separator + "qsub.sh");
    }

    private static int generateWithParams(List<String> domains, String outputDir, StringBuilder runner, int maxLength, int maxVariables, String method, int start, int depth, int frequency, int fold) {
        for (String domain : domains) {
            String target = domain + "_" + start + ".sh";
            String params = "cd " + place + ending + "\n" +
                    "module add jdk-8 \n" +
                    "java  -XX:+UseSerialGC -XX:NewSize=5000m -Xms5g  -Xmx20g  -jar SearchPruning.jar " + domain + " " + method + " " + maxLength + " " + maxVariables + " " + depth + " " + frequency + " " + fold;
            try {
                writeTo(params, outputDir + File.separator + target);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            runner.append("qsub -l select=1:ncpus=1:mem=21gb:scratch_local=1gb -l walltime=2:50:00 " + target + "\n");
            start++;
        }
        return start;
    }

    private static void writeTo(String content, String fileName) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(fileName);
        writer.write(content);
        writer.close();
    }


    private static int printSetting(int start, List<String> domains, String outputDir, StringBuilder runner, String method, int depth, int frequency, int fold) {
        int maxLength = 0;
        int maxVariables = 0;
        start = generateWithParams(domains, outputDir, runner, maxLength, maxVariables, method, start, depth, frequency, fold);
        maxLength = 2;
        maxVariables = 2;
        start = generateWithParams(domains, outputDir, runner, maxLength, maxVariables, method, start, depth, frequency, fold);
        return start;
    }
}
