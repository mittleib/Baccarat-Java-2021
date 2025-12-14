/* PROGRAMMER:  Brynne Mittleider
 * CLASS: AP Computer Science
 * ASSIGNMENT: Baccarat
 * 
 * CERTIFICATION: I certify that this work is my own and that
 *                none of it is the work of any other person.
 */

package mittleider_project_baccarat;

import java.util.ArrayList;


public class Deck {
    private ArrayList<Card> deck;
    
    public Deck(boolean shuffle) {
        deck = new ArrayList<>();
        set("clubs");
        set("diamonds");
        set("hearts");
        set("spades");
        if (shuffle) {
            shuffle();
        }
    }
    
    private void set(String suit) {
        for (int i = 2; i < 11; i++) {
            Card card = new Card(suit, String.valueOf(i));
            deck.add(card);
        }
        Card Jack = new Card(suit, "Jack");
        deck.add(Jack);
        Card Queen = new Card(suit, "Queen");
        deck.add(Queen);
        Card King = new Card(suit, "King");
        deck.add(King);
        Card Ace = new Card(suit, "Ace");
        deck.add(Ace);
    }
    
    public void shuffle() {
        for (int i = 51; i > 0; i--) {
            int j = (int)(Math.random()*(i+1));
            Card atJ = deck.get(j);
            deck.set(j, deck.get(i));
            deck.set(i, atJ);
        }
    }
    
    public Card getCard() {
        int c = (int)(Math.random()*(deck.size()));
        return deck.remove(c);
    }
    
    public boolean isEmpty() {
        if (deck.size() == 0) {
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        String print = "";
        for (Card card : deck) {
            print += card + " ";
        }
        return print.substring(0, print.length() - 1);
    }
}
