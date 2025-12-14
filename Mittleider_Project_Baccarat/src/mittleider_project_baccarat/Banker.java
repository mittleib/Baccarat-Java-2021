/* PROGRAMMER:  Brynne Mittleider
 * CLASS: AP Computer Science
 * ASSIGNMENT: Baccarat
 * 
 * CERTIFICATION: I certify that this work is my own and that
 *                none of it is the work of any other person.
 */

package mittleider_project_baccarat;


public class Banker {
    private Hand hand;
    private Shoe shoe;
    
    public Banker() {
        shoe = new Shoe();
        shoe.shuffle();
        hand = new Hand();
    }
    
    public void shuffle() {
        shoe.shuffle();
    }
    
    public boolean hasUseableShoe() {
        if (!shoe.needsShuffle()) {
            return true;
        }
        return false;
    }
    
    public Card dealCard() {
        return shoe.deal();
    }
    
    public Hand dealInitialHand() {
        Hand two = new Hand();
        two.addCard(shoe.deal());
        two.addCard(shoe.deal());
        return two;
    }
    
    public void setHand(Hand hnd) {
        hand = hnd;
    }
    
    public void addCard(Card card) {
        hand.addCard(card);
    }
    
    public int getHandScore() {
        return hand.calculateScore();
    }
    
    public String getCurrentHand() {
        return hand.toString();
    }
    
    public String getIncompleteHand() {
        int index = hand.toString().indexOf(" ");
        return hand.toString().substring(0,index);
    }
    
    @Override
    public String toString() {
        return hand.toString();
    }
    
    public void addDeck(Deck deck) {
        shoe.addDeck(deck);
    }
    
    public void clearShoe() {
        shoe = new Shoe();
    }
}
