/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sajiman.teen.pati;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @Sajan_Duwal
 */
public class SajimanTeenPati {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        PokerTable pokerTable = new PokerTable();;

        System.out.println("Enter number of players:");
        int numberOfPlayers = 0;
        boolean validNoOfPlayers = false;
        do {
            try {
                numberOfPlayers = new Scanner(System.in).nextInt();
                if (numberOfPlayers < 2 || numberOfPlayers > 17) {
                    System.out.println("\nNumber of player must be "
                            + "form 2 to 17\n Enter number of players again: ");
                    validNoOfPlayers = false;
                } else {
                    validNoOfPlayers = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nYou are not supposed to enter characte."
                        + " \n Enter number of players again: ");
                validNoOfPlayers = false;
            }
        } while (!validNoOfPlayers);
        for (int countPlayers = 0; countPlayers < numberOfPlayers; countPlayers++) {
            String position = getPosition(countPlayers);
            System.out.println("\nEnter name of " + (countPlayers + 1)
                    + "" + position + " : ");
            String name = new Scanner(System.in).next();
            pokerTable.addPlayers(name);
        }

        pokerTable.setGame();
        pokerTable.finishGame();
    }

    private static String getPosition(int countPlayers) {
        switch (countPlayers) {
            case 0:
                return "st";
            case 1:
                return "nd";
            case 2:
                return "rd";
            default:
                return "th";
        }
    }
}
