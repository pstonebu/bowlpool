public class Pick {

    int gameNumber;
    Selection selection;
    int weight;

    public Pick(int gameNumber, Selection selection, int weight) {
        this.gameNumber = gameNumber;
        this.selection = selection;
        this.weight = weight;
    }

    public Pick() {
    }

    public Pick(Pick pick) {
        this.gameNumber = pick.gameNumber;
        this.selection = pick.selection;
        this.weight = pick.weight;
    }

    public int getGameNumber() {
        return gameNumber;
    }

    public void setGameNumber(int gameNumber) {
        this.gameNumber = gameNumber;
    }

    public Selection getSelection() {
        return selection;
    }

    public void setSelection(Selection selection) {
        this.selection = selection;
    }


    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
