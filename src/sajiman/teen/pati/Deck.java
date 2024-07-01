package sajiman.teen.pati;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private final List<String> collectionCards = new ArrayList<>();

    public Deck() {
        Cards cards[] = Cards.values();
        Suits suits[] = Suits.values();

        for (int suitCounter = 0; suitCounter < suits.length; suitCounter++) {
            for (int cardCount = 0; cardCount < cards.length; cardCount++) {
                collectionCards.add(suits[suitCounter].getSuits() + cards[cardCount].getCards());
            }
        }
    }

    public List<String> getCollectionCard() {
        return collectionCards;
    }
}
