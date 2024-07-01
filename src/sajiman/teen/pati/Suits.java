package sajiman.teen.pati;

public enum Suits {
    HEART("♥"),
    SPREAD("♠"),
    DIAMOND("♦"),
    CLUB("♣");

    private final String suits;

    Suits(String suits) {
        this.suits = suits;
    }

    public String getSuits() {
        return suits;
    }
}
