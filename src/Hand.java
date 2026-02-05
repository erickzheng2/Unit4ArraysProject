import java.util.Arrays;

public class Hand {
    //{High Card, One Pair, Two Pair, Three of a Kind, Full House, Four of a Kind, Five of a Kind};
    static int handFreq[] = {0, 0, 0, 0, 0, 0, 0};
    int cards[] = new int[5];
    //this is decorative just to keep track of labels' indexs on labelFreq
    //{2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace};
    int labelFreq[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    int ranking;
    int bidValue;
    int handType;

    enum HandType{// custom enum class to make hand types more readable
        FIVE_OF_A_KIND(7), FOUR_OF_A_KIND(6), FULL_HOUSE(5), THREE_OF_A_KIND(4), TWO_PAIR(3), ONE_PAIR(2), HIGH_CARD(1);

        private final int rank;
        HandType(int rank){
            this.rank = rank;
        }

        public int getRank(){
            return rank;
        }
    }

    //Constructor
    public Hand (String[] input){
        for (int i = 0; i < 5; i++){
            try{
                cards[i] = Integer.parseInt(input[i]);
            }
            catch (NumberFormatException e){ //if the previous line tries to parse a String, this section will trigger
                if (input[i].equals("Jack")){
                    cards[i] = 11;
                }
                if (input[i].equals("Queen")){
                    cards[i] = 12;
                }
                if (input[i].equals("King")){
                    cards[i] = 13;
                }
                if (input[i].equals("Ace")){
                    cards[i] = 14;
                }
            }
        }
        bidValue = Integer.parseInt(input[5]);
    }

    void evaluateFreq(){
        for(int x = 0; x < labelFreq.length; x++){ //the indexs of labelFreq matches the labels they represent, offset by 1 so we can just use it to check the cards' labels
            for (int y = 0; y < cards.length; y++){
                if (cards[y] == x + 1){//if the label is present in cards[] increase the label frequency by 1.
                    labelFreq[x]++;
                }
            }
        }

        System.out.println(Arrays.toString(labelFreq));
    }

    void evaluateHandType(){
        String frequencies = "";
        for (int i = 0;i < labelFreq.length; i++){
            frequencies += labelFreq[i]; //converts labelFreq[] to a string so that we can use indexOf method
        }

        if (frequencies.indexOf('5') != -1){
            handType = HandType.FIVE_OF_A_KIND.getRank(); //assigns enum representation of a Five of a Kind
        }
        else if (frequencies.indexOf('4') != -1){
            handType = HandType.FOUR_OF_A_KIND.getRank();
        }
        else if (frequencies.indexOf('3') != -1 && frequencies.indexOf('2') != -1){
            handType = HandType.FULL_HOUSE.getRank();
        }
        else if (frequencies.indexOf('3') != -1){
            handType = HandType.THREE_OF_A_KIND.getRank();
        }
        else if (frequencies.indexOf('2') != -1 && frequencies.indexOf('2', (frequencies.indexOf('2') + 1)) != -1){ //if there are two 2 entries in the frequency list, it is a two pair
            handType = HandType.TWO_PAIR.getRank();
        }
        else if (frequencies.indexOf('2') != -1){
            handType = HandType.ONE_PAIR.getRank();
        }
        else{
            handType = HandType.HIGH_CARD.getRank();
        }

        handFreq[handType - 1] += 1; //Adds one to hand type frequency tracker list for the correct hand type
    }


    static void printTotalHandTypes(){
        System.out.print("Number of five of a kinds:");
        System.out.println(Hand.handFreq[6]);

        System.out.print("Number of four of a kinds:");
        System.out.println(Hand.handFreq[5]);

        System.out.print("Number of full houses:");
        System.out.println(Hand.handFreq[4]);

        System.out.print("Number of three of a kinds:");
        System.out.println(Hand.handFreq[3]);

        System.out.print("Number of two pairs:");
        System.out.println(Hand.handFreq[2]);

        System.out.print("Number of one pairs:");
        System.out.println(Hand.handFreq[1]);

        System.out.print("Number of high cards:");
        System.out.println(Hand.handFreq[0]);
    }

    public String toString(){//simple toString method to print value of cards[]
        return Arrays.toString(cards);
    }
}

//Classify the hand type by

//Iterate through the entire list of objects. Every time an object encounters another object with a lesser value than it, increase it's ranking by 1.