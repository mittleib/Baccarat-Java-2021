/* PROGRAMMER:  Brynne Mittleider
 * CLASS: AP Computer Science
 * ASSIGNMENT: Baccarat
 * 
 * CERTIFICATION: I certify that this work is my own and that
 *                none of it is the work of any other person.
 */

package mittleider_project_baccarat;


public class Punter {
    private Hand hand;
    private int chips;
    private int wins;
    private int games;
    private int name;
    
    public Punter() {
        chips = 100;
        hand = new Hand();
        name = 1;
    }
    
    public Punter(int ch, int nm) {
        chips = ch;
        hand = new Hand();
        name = nm;
    }
    
    public int getName() {
        return name;
    }
    
    public void addWin() {
        wins++;
    }
    
    public void addGame() {
        games++;
    }
    
    public int getWins() {
        return wins;
    }
    
    public int getGames() {
        return games;
    }
    
    
    public void setHand(Hand hnd) {
        hand = hnd;
    }
    
    public void addCard(Card card) {
        hand.addCard(card);
    }
    
    public int getChips() {
        return chips;
    }
    
    public void adjustChips(int num) {
        chips += num;
    }
    
    public int getHandScore() {
        return hand.calculateScore();
    }
    
    public int firstTwo() {
        return hand.calculateScore(2);
    }
    
    public String getCurrentHand() {
        return hand.toString();
    }
    
    public int getHandSize() {
        return hand.getHandSize();
    }
    
    @Override
    public String toString() {
        return hand.toString();
    }
}
