package sajiman.teen.pati;

public class Game {

    private String playerName;
    private String originalCard;
    private int sum;
    private String reason;

    public Game(String playerName, int sum, String originalCard, String reason) {
        this.playerName = playerName;
        this.originalCard = originalCard;
        this.sum = sum;
        this.reason = reason;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getOriginalCard() {
        return originalCard;
    }

    public int getSum() {
        return sum;
    }

    public String getReason() {
        return reason;
    }
}
