import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PickSet {

    String playerName;
    List<Pick> picks = new ArrayList<Pick>();
    int currentScore;
    int pointsLeft;

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public int getPointsLeft() {
        return pointsLeft;
    }

    public void setPointsLeft(int pointsLeft) {
        this.pointsLeft = pointsLeft;
    }

    public PickSet(List<Pick> picks, String playerName) {
        this.picks = picks;
        this.playerName = playerName;
    }

    public PickSet() {
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public List<Pick> getPicks() {
        return picks.stream()
                .sorted(Comparator.comparing(Pick::getGameNumber))
                .collect(Collectors.toList());
    }

    public void setPicks(List<Pick> picks) {
        this.picks = picks;
    }

    public void addPick(Pick pick) {
        picks.add(pick);
    }

    public Pick getPick(int gameNumber) {
        return (picks.stream()
                .filter(t -> t.getGameNumber() == gameNumber)
                .collect(Collectors.toList())).get(0);
    }

    public int getPotential() {
        return currentScore + pointsLeft;
    }
}
