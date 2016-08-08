import java.util.*;

public class Standings {
    Map<Integer,List<PickSet>> rankings = new TreeMap<Integer,List<PickSet>>(Collections.reverseOrder());
    List<PickSet> first;
    List<PickSet> second;
    List<PickSet> third;

    public Standings() {
    }

    public Standings(Map<Integer,List<PickSet>> rankings) {
        this.rankings = rankings;
    }

    public Map<Integer,List<PickSet>> getRankings() {
        return rankings;
    }

    public void setRankings(Map<Integer,List<PickSet>> rankings) {
        this.rankings = rankings;
    }

    public void addPickSet(PickSet pickSet, int score) {
        if (rankings.isEmpty() || !rankings.containsKey(score)) {
            List<PickSet> list = new ArrayList<PickSet>();
            list.add(pickSet);
            rankings.put(score, list);
        } else {
            rankings.get(score).add(pickSet);
        }
    }

    public List<PickSet> getFirst() {
        if (first == null) {
            initRankings();
            return getFirst();
        }
        return first;
    }

    public List<PickSet> getSecond() {
        if (second == null) {
            initRankings();
            return second == null ? new ArrayList<PickSet>() : second;
        }
        return second;
    }

    public List<PickSet> getThird() {
        if (third == null) {
            initRankings();
            return third == null ? new ArrayList<PickSet>() : third;
        }
        return third;
    }

    public void initRankings() {
        int iterations = 0;
        for (int score : rankings.keySet()) {
            if (iterations == 0) {
                first = rankings.get(score);
            } else if (iterations == 1) {
                second = rankings.get(score);
            } else if (iterations == 2) {
                third = rankings.get(score);
            } else {
                break;
            }
            iterations++;
        }
    }

    public double[] getPayoutValues() {
        double[] payouts = new double[3];
        int numFirst = getFirst().size();
        int numSecond = getSecond().size();
        int numThird = getThird().size();

        if (numFirst == 1)
        {
            payouts[0] = (6.0D * Main.numUsers);

            if (numSecond == 1)
            {
                payouts[1] = (3.0D * Main.numUsers / numSecond);
                payouts[2] = (1.0D * Main.numUsers / numThird);
            }
            else
            {
                payouts[1] = (4.0D * Main.numUsers / numSecond);
                payouts[2] = 0.0D;
            }

        }
        else if (numFirst == 2)
        {
            payouts[0] = (9.0D * Main.numUsers / numFirst);
            payouts[1] = (1.0D * Main.numUsers / numSecond);
            payouts[2] = 0.0D;
        }
        else
        {
            payouts[0] = (10.0D * Main.numUsers / numFirst);
            payouts[1] = 0.0D;
            payouts[2] = 0.0D;
        }

        return payouts;
    }
}
