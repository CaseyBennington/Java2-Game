package cbennington220final;

/**
 *
 * @author Casey
 */
public class Cbennington220final {

    public static void main(String[] args) {

        int countHand1 = 0;
        int countHand2 = 0;
        int countTies = 0;
        for (int i = 0; i < 400; i++) {
            DeckOfCards deck = new DeckOfCards();
            Hand hand = new Hand(deck);
            Hand hand2 = new Hand(deck);
            System.out.println("");
            System.out.println("Hand 1");
            hand.display();
            System.out.println("");
            hand.displayAll();
            System.out.println("");
            System.out.println("Hand 2");
            hand2.display();
            System.out.println("");
            hand2.displayAll();
            System.out.println("");

            if (hand.compareTo(hand2) == 1) {
                System.out.println("Hand 1 wins.");
                hand.display();
                System.out.print(" beats ");
                hand2.display();
                countHand1++;
            } else if (hand.compareTo(hand2) == -1) {
                System.out.println("Hand 2 wins.");
                hand2.display();
                System.out.print(" beats ");
                hand.display();
                countHand2++;
            } else if (hand.compareTo(hand2) == 0) {
                System.out.println("Tie.");
                hand.display();
                System.out.print(" ties ");
                hand2.display();
                countTies++;
            }
            System.out.println("");
            //System.out.println(hand.compareTo(hand2));
        }
        System.out.println("");
        System.out.println(countHand1);
        System.out.println(countHand2);
        System.out.println(countTies);
    }
}