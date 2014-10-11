package cbennington220final;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Casey
 */
public class DeckOfCards {

    private ArrayList<Card> cards;

    public DeckOfCards() {
        cards = new ArrayList<Card>();
        int index1 = 0;
        int index2 = 0;
        Random generator = new Random();
        Card temp;

        for (int a = 0; a <= 3; a++) {
            for (int b = 0; b <= 12; b++) {
                cards.add(new Card(a, b));
            }
        }

        for (int i = 0; i < 100; i++) {
            index1 = generator.nextInt(cards.size() - 1);
            index2 = generator.nextInt(cards.size() - 1);

            temp = (Card) cards.get(index2);
            cards.set(index2, cards.get(index1));
            cards.set(index1, temp);
        }
    }

    public Card drawFromDeck() {
        return cards.remove(cards.size() - 1);
    }

    public int getTotalCards() {
        return cards.size();   //we could use this method when making a complete poker game to see if we needed a new deck
    }
}