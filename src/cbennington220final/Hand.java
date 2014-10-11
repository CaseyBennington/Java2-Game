package cbennington220final;

/**
 *
 * @author Casey
 */
public class Hand {

    private Card[] cards;
    private int[] value;

    public Hand(DeckOfCards d) {
        value = new int[6];
        cards = new Card[5];
        for (int x = 0; x < 5; x++) {
            cards[x] = d.drawFromDeck(); //fill up cards[] array.
        }

        int[] faces = new int[14];     //miscellaneous cards that are not otherwise significant
        int[] orderedFaces = new int[5];
        boolean flush = true, straight = false;
        int sameCards = 1, sameCards2 = 1;
        int largeGroupFace = 0, smallGroupFace = 0;
        int index = 0;
        int topStraightValue = 0;

        for (int x = 0; x <= 13; x++) {
            faces[x] = 0;    // zero out array
        }
        for (int x = 0; x <= 4; x++) {
            faces[cards[x].getFace()]++;   //fill out current hand array
        }
        for (int x = 0; x < 4; x++) {
            if (cards[x].getSuit() != cards[x + 1].getSuit()) {
                flush = false;   //check for flush
            }
        }

        for (int x = 13; x >= 1; x--) //x is face of cards, faces[x] is number of cards of that face
        {
            if (faces[x] > sameCards) {
                if (sameCards == 1) //if sameCards was not assigned to already
                {
                    largeGroupFace = x;

                } else {
                    sameCards2 = sameCards; //if sameCards was assigned to, write data from
                    smallGroupFace = x;     //top group to low group            
                }

                sameCards = faces[x];         //update sameCards to new greatest sameCards value in faces

            } else if (faces[x] > sameCards2) {
                sameCards2 = faces[x];
                smallGroupFace = x;
            }
        }

        if (faces[1] == 1) //if ace, run this before because ace is highest card
        {
            orderedFaces[index] = 14;
            index++;
        }

        for (int x = 13; x >= 2; x--) {
            if (faces[x] == 1) {
                orderedFaces[index] = x; //if ace
                index++;
            }
        }

        for (int x = 1; x <= 9; x++) //can't have straight with lowest value of more than 10
        {
            if (faces[x] == 1 && faces[x + 1] == 1 && faces[x + 2] == 1 && faces[x + 3] == 1 && faces[x + 4] == 1) {
                straight = true;
                topStraightValue = x + 4; //4 above bottom value
                break;
            }
        }

        if (faces[10] == 1 && faces[11] == 1 && faces[12] == 1 && faces[13] == 1 && faces[1] == 1) //ace high
        {
            straight = true;
            topStraightValue = 14; //higher than king
        }

        for (int x = 0; x <= 5; x++) {
            value[x] = 0;
        }

        //start hand evaluation
        if (sameCards == 1) {
            value[0] = 1;
            value[1] = orderedFaces[0];
            value[2] = orderedFaces[1];
            value[3] = orderedFaces[2];
            value[4] = orderedFaces[3];
            value[5] = orderedFaces[4];
        }

        if (sameCards == 2 && sameCards2 == 1) {
            value[0] = 2;
            value[1] = largeGroupFace; //rank of pair
            value[2] = orderedFaces[0];
            value[3] = orderedFaces[1];
            value[4] = orderedFaces[2];
        }

        if (sameCards == 2 && sameCards2 == 2) //two pair
        {
            value[0] = 3;
            value[1] = largeGroupFace > smallGroupFace ? largeGroupFace : smallGroupFace; //rank of greater pair
            value[2] = largeGroupFace < smallGroupFace ? largeGroupFace : smallGroupFace;
            value[3] = orderedFaces[0];  //extra card
        }

        if (sameCards == 3 && sameCards2 != 2) {
            value[0] = 4;
            value[1] = largeGroupFace;
            value[2] = orderedFaces[0];
            value[3] = orderedFaces[1];
        }

        if (straight && !flush) {
            value[0] = 5;
            value[1] = topStraightValue;
        }

        if (flush && !straight) {
            value[0] = 6;
            value[1] = orderedFaces[0]; //tie determined by ranks of cards
            value[2] = orderedFaces[1];
            value[3] = orderedFaces[2];
            value[4] = orderedFaces[3];
            value[5] = orderedFaces[4];
        }

        if (sameCards == 3 && sameCards2 == 2) {
            value[0] = 7;
            value[1] = largeGroupFace;
            value[2] = smallGroupFace;
        }

        if (sameCards == 4) {
            value[0] = 8;
            value[1] = largeGroupFace;
            value[2] = orderedFaces[0];
        }

        if (straight && flush) {
            value[0] = 9;
            value[1] = topStraightValue;
        }
    }

    public void display() {
        String s;
        switch (value[0]) {
            case 1:
                s = "High card ";
                break;
            case 2:
                s = "Pair of " + Card.faceAsString(value[1]) + "\'s";
                break;
            case 3:
                s = "Two pair of " + Card.faceAsString(value[1]) + "'s and " + Card.faceAsString(value[2]) + "'s";
                break;
            case 4:
                s = "Three of a kind " + Card.faceAsString(value[1]) + "\'s";
                break;
            case 5:
                s = Card.faceAsString(value[1]) + " high straight";
                break;
            case 6:
                s = "Flush";
                break;
            case 7:
                s = "Full house " + Card.faceAsString(value[1]) + " over " + Card.faceAsString(value[2]);
                break;
            case 8:
                s = "Four of a kind " + Card.faceAsString(value[1]);
                break;
            case 9:
                s = "Straight flush " + Card.faceAsString(value[1]) + " high";
                break;
            default:
                s = "Error in Hand.display: value[0] contains invalid value";
        }
        System.out.print(s);
    }

    public void displayAll() {
        for (int x = 0; x < 5; x++) {
            System.out.println(cards[x]);
        }
    }

    public int compareTo(Hand that) {
        for (int x = 0; x < 6; x++) {
            if (this.value[x] > that.value[x]) {
                return 1;
            } else if (this.value[x] < that.value[x]) {
                return -1;
            }
        }
        return 0; //if hands are equal
    }
}