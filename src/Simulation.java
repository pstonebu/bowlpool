import java.util.HashMap;
import java.util.Map;

public class Simulation
{
	public ResultSet simulatedResults;

    public Map<PickSet, Double> getPayout() {
        return payout;
    }

    public Map<PickSet,Double> payout;

	public Simulation() {
		simulatedResults = new ResultSet();
        payout = new HashMap<PickSet,Double>();
        resetPayouts();
	}

    public void resetPayouts() {
        payout = new HashMap<PickSet,Double>();
        for (PickSet pickSet : Main.pickList) {
            payout.put(pickSet, 0.0);
        }
    }

	public void run() {
		simulatedResults = new ResultSet();
		simulateFrom(Main.gamesPlayed);
	}

	public void simulateFrom(int gameNumber) {
		boolean lastGame = (gameNumber+1 >= Main.numGames && Main.lastGame4Teams);
        int iterations = lastGame ? 4 : 2;
        for (int outcome = 0; outcome < iterations; outcome++) {
            Selection selection = getSelection(outcome);
            if (lastGame && Main.eliminated.contains(selection)) {
                continue;
            } else if (gameNumber != Main.numGames) {
				Result currentResult = new Result();
				currentResult.setGameNumber(gameNumber);
				currentResult.setCorrectSelection(selection);
				simulatedResults.addResult(currentResult);
			}

            if (gameNumber+1 < Main.numGames) {
				simulateFrom(gameNumber + 1);
			} else {
				//do the stuff here
                Standings standings = new Standings();
                for (PickSet pickSet : Main.pickList) {
                    standings.addPickSet(pickSet, pickSet.resultsFrom(simulatedResults) + pickSet.getCurrentScore());
                }

                double[] payoutAmounts = standings.getPayoutValues();
                //pay out first
                for (PickSet pickSet : standings.getFirst()) {
                    double newPayout = payout.get(pickSet) + payoutAmounts[0];
                    payout.put(pickSet, newPayout);
                }
                //pay out second
                for (PickSet pickSet : standings.getSecond()) {
                    double newPayout = payout.get(pickSet) + payoutAmounts[1];
                    payout.put(pickSet, newPayout);
                }

                //pay out third
                for (PickSet pickSet : standings.getThird()) {
                    double newPayout = payout.get(pickSet) + payoutAmounts[2];
                    payout.put(pickSet, newPayout);
                }

				if (lastGame && simulatedResults.getResults().isEmpty()) {
					break;
				}

			}
		}
	}

	public void simulateNextGame(Selection selection) {
        //add known result
        Result next = new Result(Main.gamesPlayed, selection);
        Main.resultSet.addResult(next);
        Main.gamesPlayed++;
        for (PickSet pickSet : Main.pickList) {
            pickSet.setPoints(Main.resultSet);
        }

        //run it
        resetPayouts();
        simulatedResults = new ResultSet();
        simulateFrom(Main.gamesPlayed);

        //reset it
        Main.resultSet.removeResult(next);
        Main.gamesPlayed--;
        for (PickSet pickSet : Main.pickList) {
            pickSet.setPoints(Main.resultSet);
        }
	}

    public static Selection getSelection(int id) {
        switch (id) {
            case 0:
                return Selection.FAVORITE;
            case 1:
                return Selection.UNDERDOG;
            case 2:
                return Selection.EXTRA1;
            case 3:
                return Selection.EXTRA2;
        }
        return null;
    }
}
