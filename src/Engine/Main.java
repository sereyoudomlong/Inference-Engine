package Engine;
import java.io.*;
import java.util.*;

/*
    Main job is to read problem from file and initialise the inference engine
 */

public class Main {
    public static String kB;
    public static String query;


    public static void main(String[] args){

        if(args.length < 2) {
            System.out.println("Usage: iEngine <method> <filename>");
            System.exit(1);
        }

        if (readFromFile(args[1])){
            iEngine Inference = new iEngine(kB, query);
            Inference.solve(args[0]);
        }

    }

    //Function to read problem from file
    public static boolean readFromFile(String filename){
        List<String> lines = new ArrayList<String>();
        try{
            FileReader reader = new FileReader(filename);
            BufferedReader text = new BufferedReader(reader);
            while (text.readLine() != null){
                lines.add(text.readLine());
            }

            if (!lines.isEmpty()){
                kB = lines.get(0);      //assign to the appropriate variable
                query = lines.get(1);
            }
            return true;                // return true if everything is going well
        }
        catch(FileNotFoundException ex) // if not print out exception
        {
            //The file didn't exist, show an error
            System.out.println("Error: File \"" + filename + "\" not found.");
            System.out.println("Please check the path to the file.");
            System.exit(1);
            return false;
        }
        catch(IOException ex)
        {
            //There was an IO error, show and error message
            System.out.println("Error in reading \"" + filename + "\". Try closing it and programs that may be accessing it.");
            System.out.println("If you're accessing this file over a network, try making a local copy.");
            System.exit(1);
            return false;
        }
    }
}
