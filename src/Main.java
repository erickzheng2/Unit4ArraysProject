import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int totalWinnings = 0;
        File f = new File ("src/data");
        //create array of Hand objects
        Hand[] hands= new Hand[(int) f.length()];

        //pulling data from the file
        try {
            Scanner s = new Scanner(f);


            //variable to keep track of which line the scanner is on
            int lineNumber = 0;
            while (s.hasNextLine()){
                //harvests the raw data from the line by splitting on , or |
                String[] handData = s.nextLine().split("[,|]");
                //create new Hand object in Hand array here. The raw data is accepted as a constructor format.
                hands[lineNumber] = new Hand(handData);
                System.out.println(hands[lineNumber].toString());

                //calculates the frequency of each label in the hand
                System.out.print("Normal Freq: ");
                hands[lineNumber].evaluateFreq();

                System.out.print("Updated Freq: ");
                hands[lineNumber].evalJacks();

                //evaluates the hand type based on
                System.out.print("Hand Type: ");
                hands[lineNumber].evaluateHandType();

                System.out.println(hands[lineNumber].handType);
                lineNumber++;
            }

    }
        catch (FileNotFoundException e){
            System.out.println("File Not Found");
        }

        Hand.printTotalHandTypes(); //print final results

//        System.out.println(Arrays.toString(hands));
//        System.out.println(hands.length);



        for (int line = 0; line < 79; line++){
            for (int otherLine = 0; otherLine < 79; otherLine++){
                if (line != otherLine) {
                hands[line].compareRank(hands[otherLine]);
                }
            }
            System.out.println((line + 1) + ": " + hands[line].ranking);
            totalWinnings += hands[line].ranking * hands[line].bidValue;
        }

        System.out.println(totalWinnings);
    }
}

//copyright 2026 Erick Zheng