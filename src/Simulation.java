import java.io.*;
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
        for (PickSet pickSet : Main2.pickList) {
            payout.put(pickSet, 0.0);
        }
    }

	public void run() {
		simulatedResults = new ResultSet();
		simulateFrom(Main2.gamesPlayed);
	}

	public void simulateFrom(int gameNumber) {
		int iterations = (gameNumber+1 == Main2.numGames && Main2.lastGame4Teams) ? 4 : 2;
        for (int outcome = 0; outcome < iterations; outcome++) {
            Result currentResult = new Result();
            currentResult.setGameNumber(gameNumber);
            currentResult.setCorrectSelection(getSelection(outcome));
            simulatedResults.addResult(currentResult);

            if (gameNumber+1 < Main2.numGames) {
				simulateFrom(gameNumber + 1);
			} else {
				//do the stuff here
                Standings standings = new Standings();
                for (PickSet pickSet : Main2.pickList) {
                    standings.addPickSet(pickSet, pickSet.resultsFrom(simulatedResults) + pickSet.getCurrentScore());
                }
                //pay out first
                for (PickSet pickSet : standings.getFirst()) {
                    double newPayout = payout.get(pickSet) + standings.getPayoutValues()[0];
                    payout.put(pickSet, newPayout);
                }
                //pay out second
                for (PickSet pickSet : standings.getSecond()) {
                    double newPayout = payout.get(pickSet) + standings.getPayoutValues()[1];
                    payout.put(pickSet, newPayout);
                }

                //pay out third
                for (PickSet pickSet : standings.getThird()) {
                    double newPayout = payout.get(pickSet) + standings.getPayoutValues()[2];
                    payout.put(pickSet, newPayout);
                }

			}
		}
	}

	public void simulateNextGame(Selection selection) {
        //add known result
        Result next = new Result(Main2.gamesPlayed, selection);
        Main2.resultSet.addResult(next);
        Main2.gamesPlayed++;
        for (PickSet pickSet : Main2.pickList) {
            pickSet.setPoints(Main2.resultSet);
        }

        //run it
        resetPayouts();
        simulatedResults = new ResultSet();
        simulateFrom(Main2.gamesPlayed);

        //reset it
        Main2.resultSet.removeResult(next);
        Main2.gamesPlayed--;
        for (PickSet pickSet : Main2.pickList) {
            pickSet.setPoints(Main2.resultSet);
        }
	}

    public Selection getSelection(int id) {
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

	public static void output(String filename)
	{
		//Grab current score and potential
		for (int i =0; i < Global.numUsers; i++)
		{
			int currentscore = 0;
			int potential = 0;

			for (int j = 0; j < (Global.gamesPlayed); j++)
			{
				currentscore = currentscore + (Global.results[2*j]*Global.picks[i][2*j]) + (Global.results[(2*j)+1]*Global.picks[i][(2*j)+1]);
			}

			for (int j = Global.gamesPlayed; j < Global.numGames+1; j++)
			{
				potential = potential + (Global.picks[i][(2*j)] + Global.picks[i][(2*j)+1]);
			}

			potential = currentscore + potential;
			Global.pANDp[i][0] = currentscore;
			Global.pANDp[i][1] = potential;
		}



		try
		{
			if (filename.equals("current.txt"))
				filename = "current (before game " + (Global.gamesPlayed+1) + ").csv";
			PrintWriter out = new PrintWriter(new FileWriter(filename));

			for (int i = 0; i < Global.numUsers; i++)
			{
				out.print("\"" + Global.users[i] + "\"," + Global.pANDp[i][0] + "," + Global.pANDp[i][1] + ",");

				//Value
				int power = Global.gamesPlayed == 26 ? 1 : (Global.numGames - Global.gamesPlayed) + 1;
				out.println(Global.payout[i] / Math.pow(2.0D, power));
			}

			out.flush();
			out.close();
		}
		catch (IOException e)
		{
			System.exit(0);
		}
	}

	public static void outputValue(String filename)
	{
		try
		{
			PrintWriter out = new PrintWriter(new FileWriter(filename));

			for (int i = 0; i < Global.numUsers; i++)
			{
				out.print(Global.users[i] + ",");
				int power = 0;

				if (Global.gamesPlayed == 26)
					power = 1;
				else
					power = Global.numGames - Global.gamesPlayed;
				out.println(Global.payout[i] / Math.pow(2.0D, power));
			}

			out.flush();
			out.close();
		}
		catch (IOException e)
		{
			System.exit(0);
		}
	}
}
