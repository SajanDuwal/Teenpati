package sajiman.teen.pati;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private List<String> playersNameList = new ArrayList<>();

    public void addPlayersName(String name) {
        playersNameList.add(name);
    }

    public List<String> getPlayerName() {
        return playersNameList;
    }
}
