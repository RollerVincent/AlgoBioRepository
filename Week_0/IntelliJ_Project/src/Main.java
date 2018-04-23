import org.apache.commons.cli.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Main {


    static int packagingIndex = 2;


    public static void main(String[] args) {

        CommandLine cmd = parseArgs(args);
        if (cmd == null) return;

        Integer[] inputArray  = parseInput(cmd.getOptionValue("i"));

        SequenceAlgorithm Implementation = getImplementation(cmd);

        String outputPath = cmd.hasOption("o") ?  cmd.getOptionValue("o") : null;

        List<Integer[]> results = packagingIndex==1 ? Implementation.SMSS(inputArray) : Implementation.AMSS(inputArray);

        produceOutput(outputPath,results);

    }


    static CommandLine parseArgs(String[] args){
        Options options = new Options();

        Option input = new Option("i", true, "input file");
        input.setRequired(true);
        options.addOption(input);

        //Wenn nicht, dann wird nach stdout geprintet
        Option output = new Option("o", true, "output file");
        options.addOption(output);

        options.addOption("n", false, "naive algorithm");
        options.addOption("l", false, "linear algorithm");
        options.addOption("d", false, "dynamic algorithm");
        options.addOption("dc", false, "divide and conquer algorithm");

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("Main", options);
            System.exit(1);
            return null;
        }

        if(!(cmd.hasOption("n")|cmd.hasOption("l")|cmd.hasOption("d")|cmd.hasOption("dc"))){
            System.out.println("Choose an algorithm flag ([-n|-l|-d|-dc])");
            formatter.printHelp("Main", options);
            System.exit(1);
            return null;
        }

        return cmd;
    }

    static Integer[] parseInput(String path){
        ArrayList<Integer> inputSequence = new ArrayList<Integer>();
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                if(!line.startsWith("#")) {
                    for (String s : line.split("\t")) {
                        inputSequence.add(Integer.parseInt(s));
                    }
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputSequence.toArray(new Integer[inputSequence.size()]);

    }

    static SequenceAlgorithm getImplementation(CommandLine cmd){
        if(cmd.hasOption("n")){
            return new Naive();
        }else if(cmd.hasOption("dc")){
            return new DivideAndConquer();
        }else if(cmd.hasOption("d")){
            return new Dynamic();
        }else if(cmd.hasOption("l")){
            return new Linear();
        }
        return null;
    }

    static void produceOutput(String path, List<Integer[]> results){
        try {
            if(path!=null) {
                PrintWriter writer = new PrintWriter(path, "UTF-8");
                for (int i = 0; i < results.size(); i++) {
                    writer.println(results.get(i)[0] + "\t" + results.get(i)[1] + "\t" + results.get(i)[2]);
                }
                writer.close();
            }else{
                for(Integer[] i: results){
                    for(int j=0; j<i.length;j++) System.out.print(i[j]+ "\t");
                    System.out.println();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}
