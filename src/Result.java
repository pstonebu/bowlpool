
public class Result {
    int gameNumber;
    Selection correctSelection;


    public Result(int gameNumber, Selection correctSelection) {
        this.gameNumber = gameNumber;
        this.correctSelection = correctSelection;
    }

    public Result() {

    }

    public Result(Result result) {
        this.gameNumber = result.gameNumber;
        this.correctSelection = result.correctSelection;
    }

    public Selection getCorrectSelection() {
        return correctSelection;
    }

    public void setCorrectSelection(Selection correctSelection) {
        this.correctSelection = correctSelection;
    }

    public int getGameNumber() {
        return gameNumber;
    }

    public void setGameNumber(int gameNumber) {
        this.gameNumber = gameNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result result = (Result) o;

        return gameNumber == result.gameNumber;

    }

    @Override
    public int hashCode() {
        return gameNumber;
    }
}
