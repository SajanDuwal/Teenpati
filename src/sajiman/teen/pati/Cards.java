package sajiman.teen.pati;

public enum Cards {
    ONE("A", 14),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    ELEVEN("J", 11),
    TWELVE("Q", 12),
    THIRTEEN("K", 13);

    private final String cards;
    private final int cardValue;

    Cards(String cards, int cardValue) {
        this.cards = cards;
        this.cardValue = cardValue;
    }

    public String getCards() {
        return cards;
    }

    public int getCardValue() {
        return cardValue;
    }
}