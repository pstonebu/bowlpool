import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scan;
		
		try
    	{
			//Get number of users
    		scan = new Scanner (new File ("picks.txt"));
    		while (scan.hasNext())
    		{
				String current = scan.nextLine();
    			Global.numUsers++;
				String[] parsed = current.split("\t");
            }
    		Global.users = new String[Global.numUsers];
    		Global.picks = new int[Global.numUsers][2*Global.numGames+3];
            Global.results = new int[Global.numGames*2+2];
            Global.tally = new int[Global.numUsers][3];
            Global.payout = new double[Global.numUsers];
            Global.pANDp = new int[Global.numUsers][2];
    		scan.close();
    		
    		scan = new Scanner (new File ("picks.txt"));
    		// Process the input file, one integer at a time
    		
    		for (int i = 0; i < Global.numUsers; i++)
    		{
				PickSet pickSet = new PickSet();
				Global.users[i] = scan.next();
				pickSet.setPlayerName(Global.users[i]);
    			
    			for (int j = 0; j < Global.numGames*2+2; j++)
    			{
    				Global.picks[i][j] = scan.nextInt();
    			}
    			
    		}
    		
    		scan = new Scanner (new File ("results.txt"));
    		
    		while (scan.hasNext())
    		{
    			@SuppressWarnings("unused")
				String team1 = scan.next();
    			int favorite = scan.nextInt();
    			int underdog = scan.nextInt();
    			@SuppressWarnings("unused")
				String team2 = scan.next();
    			
    			if (favorite == 1 || underdog == 1)
    			{
    				Global.results[2*Global.gamesPlayed] = favorite;
    				Global.results[2*Global.gamesPlayed+1] = underdog;
    				Global.gamesPlayed++;
    			}
    			else
    				break;
    		}
    		
    		long before;
    		long after;
    		odds current = new odds();
    		before = System.currentTimeMillis();
    		current.runOdds();
    		current.output("current.txt");
    		after = System.currentTimeMillis();
    		System.out.println("Current created in " + (after-before) + " ms.");
    		
    		if (Global.numGames-Global.gamesPlayed > 0)
    		{
    			odds favorite = new odds();
    			Global.results[2*Global.gamesPlayed] = 1;
    			Global.results[2*Global.gamesPlayed+1] = 0;
    			Global.gamesPlayed++;
    			before = System.currentTimeMillis();
    			favorite.runOdds();
    			String favOutput = "if fav wins game " + (Global.gamesPlayed) + ".csv";
    			favorite.output(favOutput);
    			after = System.currentTimeMillis();
        		System.out.println("Favorite created in " + (after-before) + " ms.");
    		
    			odds underdog = new odds();
    			Global.gamesPlayed--;
    			Global.results[2*Global.gamesPlayed] = 0;
    			Global.results[2*Global.gamesPlayed+1] = 1;
    			Global.gamesPlayed++;
    			before = System.currentTimeMillis();
    			underdog.runOdds();
    			String undOutput = "if und wins game " + (Global.gamesPlayed) + ".csv";
    			underdog.output(undOutput);
    			after = System.currentTimeMillis();
        		System.out.println("Underdog created in " + (after-before) + " ms.");

                new zipper().output("current (before game " + (Global.gamesPlayed) + ").csv",favOutput,undOutput,"update.txt");
        		/*
        		if (Global.numGames-Global.gamesPlayed > 0)
        		{
        			String fav = "if fav wins game " + (Global.gamesPlayed);
        			String und = "if und wins game " + (Global.gamesPlayed);
        			(new File(fav)).mkdir();
        			(new File(und)).mkdir();
        		
        			try 
        			{
        				underdog.copy(undOutput, und + "\\" + "current (before game " + (Global.gamesPlayed+1) + ").csv");
        				favorite.copy(favOutput, fav + "\\" + "current (before game " + (Global.gamesPlayed+1) + ").csv");
        			
        				//Similate if underdog wins the next game
        				Global.gamesPlayed--;
        				Global.results[2*Global.gamesPlayed] = 0;
            			Global.results[2*Global.gamesPlayed+1] = 1;
            			Global.gamesPlayed++;
            			Global.results[2*Global.gamesPlayed] = 0;
            			Global.results[2*Global.gamesPlayed+1] = 1;
            			Global.gamesPlayed++;
        				odds undund = new odds();
        				undund.runOdds();
        				undund.output(und + "\\" + "if und wins game " + (Global.gamesPlayed) + ".csv");
        				odds undfav = new odds();
        				Global.gamesPlayed--;
        				Global.results[2*Global.gamesPlayed] = 1;
            			Global.results[2*Global.gamesPlayed+1] = 0;
            			Global.gamesPlayed++;
            			undfav.runOdds();
                        new zipper().output(und + "\\" + "current (before game " + (Global.gamesPlayed-1) + ").csv",und + "\\" + "if fav wins game " + (Global.gamesPlayed) + ".csv",und + "\\" + "if und wins game " + (Global.gamesPlayed) + ".csv",und + "\\" + "ifundwinsupdate.txt");
            			
            			Global.gamesPlayed = Global.gamesPlayed-2;
            			Global.results[2*Global.gamesPlayed] = 1;
            			Global.results[2*Global.gamesPlayed+1] = 0;
            			Global.gamesPlayed++;
            			Global.results[2*Global.gamesPlayed] = 0;
            			Global.results[2*Global.gamesPlayed+1] = 1;
            			Global.gamesPlayed++;
        				odds favund = new odds();
        				favund.runOdds();
        				favund.output(fav + "\\" + "if und wins game " + (Global.gamesPlayed) + ".csv");
        				
        				Global.gamesPlayed--;
        				Global.results[2*Global.gamesPlayed] = 1;
            			Global.results[2*Global.gamesPlayed+1] = 0;
            			Global.gamesPlayed++;
        				odds favfav = new odds();
        				favfav.runOdds();
        				favfav.output(fav + "\\" + "if fav wins game " + (Global.gamesPlayed) + ".csv");
                        new zipper().output(fav + "\\" + "current (before game " + (Global.gamesPlayed-1) + ").csv",fav + "\\" + "if fav wins game " + (Global.gamesPlayed) + ".csv",fav + "\\" + "if und wins game " + (Global.gamesPlayed) + ".csv",fav + "\\" + "iffavwinsupdate.txt");

                    }
        			catch (IOException e) 
        			{
        				System.err.println(e.getMessage());
        			}
        		}  */
    			
    		}
    		
    		
    	}
		
		catch (FileNotFoundException e)
		{
			System.exit(0);
		}

	}
}
