/* PROGRAMMER:  Brynne Mittleider
 * CLASS: AP Computer Science
 * ASSIGNMENT: Baccarat
 * 
 * CERTIFICATION: I certify that this work is my own and that
 *                none of it is the work of any other person.
 */

package mittleider_project_baccarat;

import java.util.ArrayList;


public class Hand {
    private ArrayList<Card> hand;
    
    public Hand() {
        hand = new ArrayList<>();
    }
    
    public void addCard(Card card) {
        hand.add(card);
    }
    
    public Card getCard(int index) {
        return hand.get(index);
    }
    
    public int calculateScore() {
        return calculateScore(hand.size());
    }
    
    public int calculateScore(int n) {
        int score = 0;
        for (int i = 0; i < n; i++) {
            String rank = hand.get(i).getRank();
            String letter = rank.substring(0,1);
            if (!(letter.equals("K") || letter.equals("Q") || letter.equals("J"))) {
                if (hand.get(i).getRank().equals("Ace")) {
                    score += 1;
                } else {
                    if (Integer.valueOf(rank) != 10) {
                        score += Integer.valueOf(rank);
                    }
                }
            }
            
        }
        
        return score % 10;
    }
    
    
    @Override
    public String toString() {
        String print = "";
        for (Card card : hand) {
            print += card.toString() + " ";
        }
        return print.substring(0, print.length() - 1);
    }
    
    public int getHandSize() {
        return hand.size();
    }
}
