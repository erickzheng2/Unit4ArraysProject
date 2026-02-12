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

                hands[lineNumber].evaluateFreq();

                hands[lineNumber].evaluateHandType();

                lineNumber++;
            }

    }
        catch (FileNotFoundException e){
            System.out.println("File Not Found");
        }

        Hand.printTotalHandTypes(); //print part 1 Answer

        for (int line = 0; line < 79; line++){
            for (int otherLine = 0; otherLine < 79; otherLine++){
                if (line != otherLine) {
                hands[line].compareRank(hands[otherLine]);
                }
            }
            //System.out.println((line + 1) + ": " + hands[line].ranking);
            totalWinnings += hands[line].ranking * hands[line].bidValue;
        }

        System.out.println("Part 2: " + totalWinnings); //print part 2 Answer



        //PART 3

        totalWinnings = 0;
        Hand[] jHands= new Hand[(int) f.length()];

        //pulling data from the file
        try {
            Scanner s = new Scanner(f);


            //variable to keep track of which line the scanner is on
            int lineNumber = 0;
            while (s.hasNextLine()){
                //harvests the raw data from the line by splitting on , or |
                String[] handData = s.nextLine().split("[,|]");
                //create new Hand object in Hand array here. The raw data is accepted as a constructor format.
                jHands[lineNumber] = new Hand(handData);

                jHands[lineNumber].evaluateFreq();

                jHands[lineNumber].evalJacks();

                jHands[lineNumber].evaluateHandType();

                lineNumber++;
            }

        }
        catch (FileNotFoundException e){
            System.out.println("File Not Found");
        }


        for (int line = 0; line < 79; line++){
            for (int otherLine = 0; otherLine < 79; otherLine++){
                if (line != otherLine) {
                    jHands[line].compareRankWildVers(jHands[otherLine]);
                }
            }
            //System.out.println((line + 1) + ": " + hands[line].ranking);
            totalWinnings += jHands[line].ranking * jHands[line].bidValue;
        }

        System.out.println("Part 3: " + totalWinnings); //print part 2 Answer


    }
}

//copyright 2026 Erick Zheng