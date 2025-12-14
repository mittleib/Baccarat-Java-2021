/* PROGRAMMER:  Brynne Mittleider
 * CLASS: AP Computer Science
 * ASSIGNMENT: Baccarat
 * 
 * CERTIFICATION: I certify that this work is my own and that
 *                none of it is the work of any other person.
 */

package mittleider_project_baccarat;

import java.util.ArrayList;


public class Shoe {
    private ArrayList<Card> shoe;
    private int originalSize;
    
    public Shoe() {
        shoe = new ArrayList<>();
        originalSize = 0;
    }
    
    public void addDeck(Deck deck) {
        while (!deck.isEmpty()) {
            shoe.add(deck.getCard());
        }
        originalSize += 52;
        
    }
    
    public void shuffle() {
        for (int i = shoe.size() - 1; i > 0; i--) {
            int j = (int)(Math.random()*(i+1));
            Card atJ = shoe.get(j);
            shoe.set(j, shoe.get(i));
            shoe.set(i, atJ);
        }
    }
    
    public Card deal() {
        return shoe.remove(0);
    }
    
    public boolean needsShuffle() {
        if (shoe.size() < (originalSize) / 4) {
            return true;
        }
        return false;
    }
}
