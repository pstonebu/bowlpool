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

    public PickSet(PickSet pickSet) {
        this.playerName = pickSet.playerName;
        this.currentScore = pickSet.currentScore;
        this.pointsLeft = pickSet.pointsLeft;

        for (Pick pick : pickSet.picks) {
            this.picks.add(new Pick(pick));
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public List<Pick> getPicks() {
        /*return picks.stream()
                .sorted(Comparator.comparing(Pick::getGameNumber))
                .collect(Collectors.toList());*/
        return picks;
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

    public void setPoints(ResultSet resultSet) {
        int points = 0;
        int left = 0;
        for (Result result : resultSet.getResults()) {
            Pick matchingPick = getPicks().get(result.getGameNumber());
            if (result.getCorrectSelection().equals(matchingPick.getSelection())) {
                points += matchingPick.getWeight();
            }
        }
        for (int i = Main2.gamesPlayed; i < Main2.numGames; i++) {
            Pick matchingPick = getPicks().get(i);
            if (matchingPick.getGameNumber() == Main2.numGames-1 && Main2.lastGame4Teams && Main2.eliminated.contains(matchingPick.getSelection())) {
                continue;
            }
            left += matchingPick.getWeight();
        }

        this.pointsLeft = left;
        this.currentScore = points;
    }

    public int resultsFrom(ResultSet resultSet) {
        int points = 0;
        for (Result result : resultSet.getResults()) {
            Pick matchingPick = getPicks().get(result.getGameNumber());
            if (matchingPick.getSelection().equals(result.getCorrectSelection())) {
                points += matchingPick.getWeight();
            }
        }
        return points;
    }
}
