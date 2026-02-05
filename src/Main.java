import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //pulling data from the file
        try {
            File f = new File ("src/data");
            Scanner s = new Scanner(f);
            //create array of Hand objects
            Hand[] hands= new Hand[(int) f.length()];

            //variable to keep track of which line the scanner is on
            int lineNumber = 0;
            while (s.hasNextLine()){
                //harvests the raw data from the line by splitting on , or |
                String[] handData = s.nextLine().split("[,|]");
                //create new Hand object in Hand array here. The raw data is accepted as a constructor format.
                hands[lineNumber] = new Hand(handData);
                System.out.println(hands[lineNumber].toString());

                //calculates the frequency of each label in the hand
                hands[lineNumber].evaluateFreq();
                //evaluates the hand type based on frequency
                hands[lineNumber].evaluateHandType();

                System.out.println(hands[lineNumber].handType);
                lineNumber++;
            }

    }
        catch (FileNotFoundException e){
            System.out.println("File Not Found");
        }

        Hand.printTotalHandTypes(); //print final results

    }
}

//copyright 2026 Erick Zheng