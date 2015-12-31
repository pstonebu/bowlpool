
public class Rank {
    public Rank(PickSet pickSet, int score) {
        this.pickSet = pickSet;
        this.score = score;
    }

    public PickSet getPickSet() {
        return pickSet;
    }

    public void setPickSet(PickSet pickSet) {
        this.pickSet = pickSet;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public PickSet pickSet;
    public int score;
}
