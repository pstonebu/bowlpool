import java.util.List;

public class PickSet {

    String playerName;
    List<Pick> picks;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public List<Pick> getPicks() {
        return picks;
    }

    public void setPicks(List<Pick> picks) {
        this.picks = picks;
    }

    public void addPick(Pick pick) {
        picks.add(pick);
    }

}
