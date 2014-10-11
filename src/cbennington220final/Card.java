package cbennington220final;

/**
 *
 * @author Casey
 */
public class Card {

    private int face, suit;
    private static String[] suits = {"Hearts", "Spades", "Diamonds", "Clubs"};
    private static String[] faces = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

    public static String faceAsString(int face) {
        return faces[face];
    }

    public Card(int suit, int face) {
        this.face = face;
        this.suit = suit;
    }

    public @Override
    String toString() {
        return faces[face] + " of " + suits[suit];
    }

    public int getFace() {
        return face;
    }

    public int getSuit() {
        return suit;
    }
}