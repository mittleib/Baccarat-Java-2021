/* PROGRAMMER:  Brynne Mittleider
 * CLASS: AP Computer Science
 * ASSIGNMENT: Baccarat
 * 
 * CERTIFICATION: I certify that this work is my own and that
 *                none of it is the work of any other person.
 */

package mittleider_project_baccarat;

import java.util.Scanner;


public class Baccarat {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Hi! Welcome to Baccarat.");
        boolean nPlay = false;
        String nPlayersDraft = "";
        while (!nPlay) {
            System.out.print("How many people will be playing? ");
            nPlayersDraft = input.nextLine();
            nPlay = isNum(nPlayersDraft);
        }
        int nPlayers = Integer.valueOf(nPlayersDraft);
        
        Punter[] punters = new Punter[nPlayers];
        for (int i = 0; i < nPlayers; i++) {
            punters[i] = new Punter(100, (i + 1));
        }
        
        boolean nDe = false;
        String nDecksDraft = "";
        int nDecks = 0;
        while (nDecks < 2) {
                System.out.print("How many card decks should be placed in the shoe? ");
                nDecksDraft = input.nextLine();
                nDe = isNum(nDecksDraft);
                if (nDe) {
                   nDecks = Integer.valueOf(nDecksDraft); 
                }
        }
        Banker banker = new Banker();
        rebuildShoe(nDecks, banker);
        
        int bankWins = 0;
        int bankGames = 0;
        for (int i = 0; i < punters.length; i++) {
            System.out.println("Punter " + (i + 1) + "'s turn");
            Punter dude = new Punter(100, (i + 1));
            boolean go = true;
            while (go) {
                System.out.println("You have " + punters[i].getChips());
                boolean bSz = false;
                String betSizeDraft = "";
                while (!bSz) {
                    System.out.print("What is your bet? ");
                    betSizeDraft = input.nextLine();
                    bSz = isNum(betSizeDraft);
                    if (bSz) {
                        if (Integer.valueOf(betSizeDraft) > dude.getChips()) {
                            bSz = false;
                        } 
                    }
                }
                int betSize = Integer.valueOf(betSizeDraft);
                
                if (!banker.hasUseableShoe()) {
                    System.out.println("The shoe has less than 25% of its cards left.");
                    System.out.println("It is being rebuilt.");
                    rebuildShoe(nDecks, banker);
                }
                dude.addCard(banker.dealCard());
                dude.addCard(banker.dealCard());
                banker.addCard(banker.dealCard());
                banker.addCard(banker.dealCard());
                System.out.print("Banker's hand: " + banker.getIncompleteHand());
                System.out.println("");
                System.out.print("Punter's hand: " + dude.toString());
                System.out.println("");
                boolean takeCards = true;
                while (takeCards) {
                    System.out.print("Punter, do you wish to add another card? ");
                    String aCard = input.nextLine();
                    while (!(aCard.equals("yes")  || aCard.equals("no"))) {
                        System.out.print("Punter, do you wish to add another card? ");
                        aCard = input.nextLine();
                    }
                    if (aCard.equals("yes")) {
                        dude.addCard(banker.dealCard());
                        System.out.println("Punter's hand: " + dude.toString());
                    } else {
                        takeCards = false;
                    }
                    if (dude.getHandSize() >= 6) {
                        takeCards = false;
                    }
                }
                System.out.println("Banker's hand: " + banker.toString());
                System.out.println("Punter's hand: " + dude.toString());
                int dScore = dude.getHandScore();
                int bScore = banker.getHandScore();
                boolean dWins = false;
                if (dScore > bScore) {
                    System.out.println("The winner is the Punter.");
                    dWins = true;
                    dude.addWin();
                } else {
                    System.out.println("The winner is the Banker.");
                    dude.adjustChips(betSize * -1);
                    bankWins++;
                }
                if (dude.firstTwo() == 9 && dWins) {
                    dude.adjustChips(betSize * 3);
                }
                if (dude.firstTwo() == 8 && dWins) {
                    dude.adjustChips(betSize * 2);
                }
                if (dude.firstTwo() <= 7 && dWins) {
                    dude.adjustChips(betSize);
                }
                if (dude.getChips() <= 0) {
                    System.out.println("You have no chips.");
                } else {
                    System.out.println("You have " + dude.getChips() + " chips.");
                }
                
                if (dude.getChips() > 0) {
                    System.out.print("Do you wish to play again? ");
                    String play = input.nextLine();
                    while (!(play.equals("yes")  || play.equals("no"))) {
                        System.out.print("Do you wish to play again? ");
                        play = input.nextLine();
                    }
                    if (play.equals("no")) {
                        go = false;
                    }
                } else {
                    System.out.println("Game over. Do better next time.");
                    go = false;
                }
                dude.addGame();
                bankGames++;
                dude.setHand(new Hand());
                banker.setHand(new Hand());
                punters[i] = dude;
            }
            if (dude.getChips() > 100) {
                System.out.println("Congratulations for your success!");
            } else {
                System.out.println("Better luck next time.");
            }
            System.out.println("");
        }
        System.out.println("Totals");
        System.out.println("");
        System.out.println("Banker: " + bankWins + "/" + bankGames + " = " + (int)Math.round((double)bankWins/bankGames * 100) + "% wins");
        
        int[] players = new int[punters.length];
        insertionSort(punters, players);
        
        
        
        for (int i = 0; i < punters.length; i++) {
            System.out.println("Player " + punters[i].getName() + ": " + punters[i].getWins() + "/" + punters[i].getGames() + " = " + (int)Math.round((double)punters[i].getWins() / punters[i].getGames() * 100.0) + "% wins. "
                    + "They have " + punters[i].getChips() + " chips.");
        }
    }
    
    private static void rebuildShoe(int nDecks, Banker banker) {
        banker.clearShoe();
        for (int i = 0; i < nDecks; i++) {
            Deck deck = new Deck(false);
            banker.addDeck(deck);
        }
        banker.shuffle();
    }
    
    public static boolean isNum(String word) {
        if (word.length() == 0) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            String letter = word.substring(i, i + 1);
            if (!(letter.equals("0") || letter.equals("1") || letter.equals("2") || letter.equals("3") ||
                    letter.equals("4") || letter.equals("5") || letter.equals("6")
                    || letter.equals("7") || letter.equals("8") || letter.equals("9"))) {
                return false;
            }
        }
        if (Integer.valueOf(word) < 1) {
            return false;
        }
        return true;
    }
    
    public static void insertionSort(Punter[] punters, int[] players) {
        for (int i = 1; i < punters.length; i++) {
            int j = i;
            int val = punters[j].getChips();
            Punter pun = punters[j];
            
            while (j > 0 && val > punters[j - 1].getChips()) {
                punters[j] = punters[j - 1];
                j--;
            }
            punters[j] = pun;
        }
    }
}
