/* PROGRAMMER:  Brynne Mittleider
 * CLASS: AP Computer Science
 * ASSIGNMENT: Baccarat
 * 
 * CERTIFICATION: I certify that this work is my own and that
 *                none of it is the work of any other person.
 */

package mittleider_project_baccarat;


public class Card {
    private String suit;
    private String rank;
    
    public Card() {
        suit = "clubs";
        rank = "2";
    }
    
    public Card(String st, String rk) {
        suit = st;
        rank = rk;
    }
    
    @Override
    public String toString() {
        String print;
        if (rank.equals("10")) {
            print = rank + suit.substring(0,1).toUpperCase();
        } else {
            print = rank.substring(0,1).toUpperCase() + suit.substring(0,1).toUpperCase();
        }
        
        return print;
    }
    
    public String getRank() {
        return rank;
    }
}
