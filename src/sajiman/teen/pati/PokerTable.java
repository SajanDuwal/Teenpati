package sajiman.teen.pati;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokerTable {

    private Deck deck;
    private Players players;
    private List<String> randomCollectionCard = new ArrayList<>();
    private List<String> playerList = new ArrayList<>();
    private List<String> cardList = new ArrayList<>();
    private Map<String, List<String>> finalList = new HashMap<>();
    private List<Game> resultList = new ArrayList<>();

    public PokerTable() {
        deck = new Deck();
        players = new Players();
    }

    public void shuffleCollectionCard() {
        Collections.shuffle(deck.getCollectionCard());
        randomCollectionCard.addAll(deck.getCollectionCard());
    }

    public List<String> getShuffledCollectionCard() {
        return randomCollectionCard;
    }

    public void addPlayers(String playerName) {
        players.addPlayersName(playerName);
    }

    public void setGame() {
        shuffleCollectionCard();
        int cardTemp = 0;
        for (int playerCount = 0; playerCount < players.getPlayerName().size(); playerCount++) {
            cardTemp = playerCount;
            playerList.add(players.getPlayerName().get(playerCount));
            for (int cardCount = 0; cardCount < 3; cardCount++) {
                cardList.add(randomCollectionCard.get(cardTemp));
                cardTemp += players.getPlayerName().size();
            }
        }
        int tempCard = 0;
        for (int count = 0; count < players.getPlayerName().size(); count++) {
            finalList.put(playerList.get(count), Arrays.asList(cardList.get(tempCard++),
                    cardList.get(tempCard++), cardList.get(tempCard++)));
        }
    }

    public Map getFinalList() {
        return finalList;
    }

    public void finishGame() {
        cardIdentify();
        result();
    }

    private void cardIdentify() {
        int[] seq = new int[3];
        for (int mainCounter = 0; mainCounter < players.getPlayerName().size(); mainCounter++) {
            String player = players.getPlayerName().get(mainCounter);
            List<String> playerCard = finalList.get(player);
            String originalCard = finalList.get(player).toString();

            String firstCardCombined = playerCard.get(0);
            String secondCardCombined = playerCard.get(1);
            String thirdCardCombined = playerCard.get(2);

//            System.out.println(firstCardCombined + secondCardCombined + thirdCardCombined);
            String firstSuit = firstCardCombined.substring(0, 1);
            String secondSuit = secondCardCombined.substring(0, 1);
            String thirdSuit = thirdCardCombined.substring(0, 1);

//            System.out.println(firstSuit + secondSuit + thirdSuit);
            String firstCard = firstCardCombined.substring(1);
            String secondCard = secondCardCombined.substring(1);
            String thirdCard = thirdCardCombined.substring(1);

//            System.out.println(firstCard + secondCard + thirdCard);
            seq[0] = cardValue(firstCard);
            seq[1] = cardValue(secondCard);
            seq[2] = cardValue(thirdCard);

            Arrays.sort(seq);

//            System.out.println(seq[0] + " " + seq[1] + " " + seq[2]);
            int total = 0;
            int sum = 0;
            String reason = null;

            if (seq[0] == 2 && seq[1] == 3 && seq[2] == 14) {
                seq[0] = 1;
                seq[1] = 2;
                seq[2] = 3;
            }

            if (firstCard.equals(secondCard) && secondCard.equals(thirdCard) && thirdCard.equals(firstCard)) {
                total = 5000000;  // for Triple
                sum = priority(total, seq);
                reason = "Reason : Triple";
            } else if (seq[0] + 1 == seq[1] && seq[0] + 2 == seq[2]) {
                total = 3000000;  // sequence
                if (seq[0] == 1 && seq[1] == 2 && seq[2] == 3) {
                    seq[0] = 2;
                    seq[1] = 3;
                    seq[2] = 14;
                    reason = "Reason : Langadi";
                } else if (seq[0] == 11 && seq[1] == 12 && seq[2] == 13) {
                    reason = "Reason : Takdir";
                } else if (seq[0] == 12 && seq[1] == 13 && seq[2] == 14) {
                    reason = "Reason : Qwapi";
                } else {
                    reason = "Reason : Sequence";
                }

                if (firstSuit.equals(secondSuit) && secondSuit.equals(thirdSuit) && thirdSuit.equals(firstSuit)) {
                    total += 1000000; // pure sequence
                    if (seq[0] == 2 && seq[1] == 3 && seq[2] == 14) {
                        reason = "Reason : Pure Langadi";
                    } else if (seq[0] == 11 && seq[1] == 12 && seq[2] == 13) {
                        reason = "Reason : Pure Takdir";
                    } else if (seq[0] == 12 && seq[1] == 13 && seq[2] == 14) {
                        reason = "Reason : Pure Qwapi";
                    } else {
                        reason = "Reason : Pure Sequence";
                    }
                }
                sum = priority(total, seq);
            } else if (firstSuit.equals(secondSuit) && secondSuit.equals(thirdSuit) && thirdSuit.equals(firstSuit)) {
                total = 2000000; // for color
                sum = priority(total, seq);
                reason = "Reason : Color";
            } else if (firstCard.equals(secondCard) || secondCard.equals(thirdCard) || thirdCard.equals(firstCard)) {
                total = 1500000; // for Double
                int tempMidCard = seq[1];
                for (int j = 2; j <= 14; j++) {
                    if (tempMidCard == j) {
                        sum = total + seq[0] + seq[1] + seq[2] + (tempMidCard * tempMidCard * tempMidCard);
                    }
                }
                reason = "Reason : Double";
            } else {
                sum = priority(total, seq);
                reason = "Reason : Highest Order";
            }

            System.out.println("================================");
            System.out.println("Player Name: " + players.getPlayerName().get(mainCounter));
            System.out.println("Player Card: " + originalCard);
            System.out.println(reason);
            resultList.add(new Game(players.getPlayerName().get(mainCounter), sum, originalCard, reason));
        }
    }

    private int cardValue(String card) {
        int val = 0;
        for (Cards cards : Cards.values()) {
            if (cards.getCards().equals(card)) {
                val = cards.getCardValue();
            }
        }
        return val;
    }

    private int priority(int total, int[] seq) {
        int minSeq = 2;
        int maxSeq = 14;
        total += seq[0] + seq[1] + seq[2];
        int[] seqValue = {600, 800, 1000, 1200, 1400, 1600, 1800, 2000, 2200, 2400, 2600, 2800, 3000};
        for (int i = minSeq; i <= maxSeq; i++) {
            if (seq[2] == i) {
                total += seqValue[i - minSeq];
                break;
            }
        }
        return total;
    }

    private void result() {
        resultList.sort(Comparator.comparing(g -> g.getSum()));
        Collections.reverse(resultList);
        Game winner = resultList.get(0);
        System.out.println("============== WINNER PLAYER ===============");
        System.out.println("Player Name: " + winner.getPlayerName());
        System.out.println("Cards: " + winner.getOriginalCard());
        System.out.println(winner.getReason());
    }

}
