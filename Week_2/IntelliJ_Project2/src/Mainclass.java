import java.io.*;
import java.util.*;

import org.apache.commons.cli.*;

public class Mainclass {

    public static void main(String[] args) {
        CommandLine cmd = parseArgs(args);
        if (cmd == null) return;
        int[] inputArray  = parseInput(cmd.getOptionValue("i"));

        Sorting implementation = getImplementation(cmd);

        String outputPath = cmd.hasOption("o") ?  cmd.getOptionValue("o") : null;

        implementation.sort(inputArray);
        produceOutput(outputPath, inputArray);

    }

    //i: Input Datei
    //-s: Insertionsort
    //-m: Mergesort
    //-o: Outputfile (optional)
    private static CommandLine parseArgs(String[] args){
        Options options = new Options();

        Option input = new Option("i", true, "input file");
        input.setRequired(true);
        options.addOption(input);

        //Wenn nicht, dann wird nach stdout geprintet
        Option output = new Option("o", true, "output file");
        options.addOption(output);


        options.addOption("s", false, "insertionsort");
        options.addOption("m", false, "mergesort");

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("Mainclass", options);
            System.exit(1);
            return null;
        }

        if(!(cmd.hasOption("s")|cmd.hasOption("m"))){
            System.out.println("Choose an algorithm flag ([-s|-m])");
            formatter.printHelp("Mainclass", options);
            System.exit(1);
            return null;
        }

        return cmd;
    }


    private static int[] parseInput(String path){
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

        return inputSequence.stream().mapToInt(i->i).toArray();

    }

    private static Sorting getImplementation(CommandLine cmd){
        if(cmd.hasOption("s")){
            return new Insertionsort();
        }else if(cmd.hasOption("m")){
            return new Mergesort();
        }
        return null;
    }


    private static void produceOutput(String path, int[] results){
        try {
            if(path!=null) {
                PrintWriter writer = new PrintWriter(path, "UTF-8");
                for (int i = 0; i < results.length; i++) {
                    writer.print(results[i] + "\t");
                }
                writer.close();
            }else{
                for(int i=0; i<results.length; i++){
                    System.out.print(results[i] + "\t");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}
