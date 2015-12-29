import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class odds 
{
	public static int[][] matrix;
	
	public odds()
	{
		matrix = new int[Global.numUsers][2*Global.numGames+3];
		reset();
		Global.tally = new int[Global.numUsers][3];
        Global.payout = new double[Global.numUsers];
        Global.pANDp = new int[Global.numUsers][2];
	}
	
	public static void reset()
	{
		for (int row = 0; row < Global.numUsers; row++) {
			for (int col = 0; col <= 2 * Global.numGames+2; col++) {
				matrix[row][col] = Global.picks[row][col];
			}
		}
	}
	
	public static void runOdds()
	{
		int total = Global.numGames-Global.gamesPlayed;
		
		/*if (total > 3)
		{
			for (int i = 0; i < 2; i++)
			{
				for (int j = 0; j < 2; j++)
				{
					new Thread(new BowlThread(i,j)).run();
				}
			}
		}
		else
		{*/
		
		if(total > 0)
		{
			for (int a = 0; a < 4; a++) {
				if (a == 0) {
                    Global.results[50] = 1;
                    Global.results[51] = 0;
                    Global.results[52] = 0;
                    Global.results[53] = 0;
                } else if (a == 1) {
                    Global.results[50] = 0;
                    Global.results[51] = 1;
                    Global.results[52] = 0;
                    Global.results[53] = 0;
                } else if (a == 2) {
                    Global.results[50] = 0;
                    Global.results[51] = 0;
                    Global.results[52] = 1;
                    Global.results[53] = 0;
                } else if (a == 3) {
                    Global.results[50] = 0;
                    Global.results[51] = 0;
                    Global.results[52] = 0;
                    Global.results[53] = 1;
                }
				if (total > 1) {
					for (Global.results[48] = 0; Global.results[48] < 2; Global.results[48]++) {
						Global.results[49] = 1 - Global.results[48];
						if (total > 2) {
							for (Global.results[46] = 0; Global.results[46] < 2; Global.results[46]++) {
								Global.results[47] = 1 - Global.results[46];
								if (total > 3) {
									for (Global.results[44] = 0; Global.results[44] < 2; Global.results[44]++) {
										Global.results[45] = 1 - Global.results[44];
										if (total > 4) {
											for (Global.results[42] = 0; Global.results[42] < 2; Global.results[42]++) {
												Global.results[43] = 1 - Global.results[42];
												if (total > 5) {
													for (Global.results[40] = 0; Global.results[40] < 2; Global.results[40]++) {
														Global.results[41] = 1 - Global.results[40];
														if (total > 6) {
															for (Global.results[38] = 0; Global.results[38] < 2; Global.results[38]++) {
																Global.results[39] = 1 - Global.results[38];
																if (total > 7) {
																	for (Global.results[36] = 0; Global.results[36] < 2; Global.results[36]++) {
																		Global.results[37] = 1 - Global.results[36];
																		if (total > 8) {
																			for (Global.results[34] = 0; Global.results[34] < 2; Global.results[34]++) {
																				Global.results[35] = 1 - Global.results[34];
																				if (total > 9) {
																					for (Global.results[32] = 0; Global.results[32] < 2; Global.results[32]++) {
																						Global.results[33] = 1 - Global.results[32];
																						if (total > 10) {
																							for (Global.results[30] = 0; Global.results[30] < 2; Global.results[30]++) {
																								Global.results[31] = 1 - Global.results[30];
																								if (total > 11) {
																									for (Global.results[28] = 0; Global.results[28] < 2; Global.results[28]++) {
																										Global.results[29] = 1 - Global.results[28];
																										if (total > 12) {
																											for (Global.results[26] = 0; Global.results[26] < 2; Global.results[26]++) {
																												Global.results[27] = 1 - Global.results[26];
																												if (total > 13) {
																													for (Global.results[24] = 0; Global.results[24] < 2; Global.results[24]++) {
																														Global.results[25] = 1 - Global.results[24];
																														if (total > 14) {
																															for (Global.results[22] = 0; Global.results[22] < 2; Global.results[22]++) {
																																Global.results[23] = 1 - Global.results[22];
																																if (total > 15) {
																																	for (Global.results[20] = 0; Global.results[20] < 2; Global.results[20]++) {
																																		Global.results[21] = 1 - Global.results[20];
																																		if (total > 16) {
																																			for (Global.results[18] = 0; Global.results[18] < 2; Global.results[18]++) {
																																				Global.results[19] = 1 - Global.results[18];
																																				if (total > 17) {
																																					for (Global.results[16] = 0; Global.results[16] < 2; Global.results[16]++) {
																																						Global.results[17] = 1 - Global.results[16];
																																						if (total > 18) {
																																							for (Global.results[14] = 0; Global.results[14] < 2; Global.results[14]++) {
																																								Global.results[15] = 1 - Global.results[14];
																																								if (total > 19) {
																																									for (Global.results[12] = 0; Global.results[12] < 2; Global.results[12]++) {
																																										Global.results[13] = 1 - Global.results[12];
																																										if (total > 20) {
																																											for (Global.results[10] = 0; Global.results[10] < 2; Global.results[10]++) {
																																												Global.results[11] = 1 - Global.results[10];
																																												if (total > 21) {
																																													for (Global.results[8] = 0; Global.results[8] < 2; Global.results[8]++) {
																																														Global.results[9] = 1 - Global.results[8];
																																														if (total > 22) {
																																															for (Global.results[6] = 0; Global.results[6] < 2; Global.results[6]++) {
																																																Global.results[7] = 1 - Global.results[6];
																																																if (total > 23) {
																																																	for (Global.results[4] = 0; Global.results[4] < 2; Global.results[4]++) {
																																																		Global.results[5] = 1 - Global.results[4];
																																																		if (total > 24) {
																																																			for (Global.results[2] = 0; Global.results[2] < 2; Global.results[2]++) {
																																																				Global.results[3] = 1 - Global.results[2];
																																																				if (total > 25) {
																																																					for (Global.results[0] = 0; Global.results[0] < 2; Global.results[0]++) {
																																																						Global.results[1] = 1 - Global.results[0];
																																																						calc();
																																																					}
																																																				} else
																																																					calc();
																																																			}
																																																		} else
																																																			calc();
																																																	}
																																																} else
																																																	calc();
																																															}
																																														} else
																																															calc();
																																													}
																																												} else
																																													calc();
																																											}
																																										} else
																																											calc();
																																									}
																																								} else
																																									calc();
																																							}
																																						} else
																																							calc();
																																					}
																																				} else
																																					calc();
																																			}
																																		} else
																																			calc();
																																	}
																																} else
																																	calc();

																															}
																														} else
																															calc();

																													}
																												} else
																													calc();

																											}
																										} else
																											calc();

																									}
																								} else
																									calc();
																							}
																						} else
																							calc();
																					}
																				} else
																					calc();
																			}
																		} else
																			calc();
																	}
																} else
																	calc();
															}
														} else
															calc();

													}
												} else
													calc();
											}
										} else
											calc();
									}
								} else
									calc();
							}
						} else
							calc();
					}
				} else
					calc();
			}
		}
		else
			calc();
		
	}
	
	public static void sumRows()
	{
		for (int row = 0; row < Global.numUsers; row++)
		{
			matrix[row][2*Global.numGames+2] = 0;
			
			for (int col = 0; col < 2*Global.numGames+2; col++)
			{
				matrix[row][2*Global.numGames+2] = matrix[row][2*Global.numGames+2]+
												 matrix[row][col];
			}
		}
			
	}
	
	public static void multCols()
	{		
		for (int row = 0; row < Global.numUsers; row++)
		{
			for (int col = 0; col < 2*Global.numGames+2; col++)
			{
				matrix[row][col] = matrix[row][col] * Global.results[col];
			}
		}
		
	}
	
	public static void findWinners()
	{
		boolean[][] winners = new boolean[Global.numUsers][3];
		
		int first = 0;
		int second = 0;
		int third = 0;
		int numFirst = 0;
		int numSecond = 0;
		int numThird = 0;
		
		//Finding first
		for (int i = 0; i < Global.numUsers; i++)
		{
			if (matrix[i][2*Global.numGames+2]>first)
			{
				numFirst = 1;
				first = matrix[i][2*Global.numGames+2];
				winners[i][0] = true;
				for (int j = 0; j < i; j++)
					winners[j][0] = false;
			}
			else if (matrix[i][2*Global.numGames+2]==first)
			{
				winners[i][0] = true;
				numFirst++;
			}
		}

		
		//Finding second
		if (numFirst < 4)
		{
		for (int i = 0; i < Global.numUsers; i++)
		{
			if (matrix[i][2*Global.numGames+2]>second && matrix[i][2*Global.numGames+2]<first)
			{
				numSecond = 1;
				second = matrix[i][2*Global.numGames+2];
				winners[i][1] = true;
				for (int j = 0; j < i; j++)
					winners[j][1] = false;
			}
			else if (matrix[i][2*Global.numGames+2]==second)
			{
				winners[i][1] = true;
				numSecond++;
			}
		}
		}
		
		//Finding third
		if (numFirst + numSecond < 3)
		{
		for (int i = 0; i < Global.numUsers; i++)
		{
			if (matrix[i][2*Global.numGames+2]>third && matrix[i][2*Global.numGames+2]<second)
			{
				numThird = 1;
				third = matrix[i][2*Global.numGames+2];
				winners[i][2] = true;
				for (int j = 0; j < i; j++)
					winners[j][2] = false;
			}
			else if (matrix[i][2*Global.numGames+2]==third)
			{
				winners[i][2] = true;
				numThird++;
			}
		}
		}

        double[] payouts = new double[3];

        if (numFirst == 1)
        {
            payouts[0] = (6.0D * Global.numUsers);

            if (numSecond == 1)
            {
                payouts[1] = (3.0D * Global.numUsers / numSecond);
                payouts[2] = (1.0D * Global.numUsers / numThird);
            }
            else
            {
                payouts[1] = (4.0D * Global.numUsers / numSecond);
                payouts[2] = 0.0D;
            }

        }
        else if (numFirst == 2)
        {
            payouts[0] = (9.0D * Global.numUsers / numFirst);
            payouts[1] = (1.0D * Global.numUsers / numSecond);
            payouts[2] = 0.0D;
        }
        else
        {
            payouts[0] = (10.0D * Global.numUsers / numFirst);
            payouts[1] = 0.0D;
            payouts[2] = 0.0D;
        }

        for (int row = 0; row < Global.numUsers; row++)
        {
            for (int col = 0; col < 3; col++)
            {
                if (winners[row][col])
                {
                    Global.tally[row][col] += 1;
                    Global.payout[row] += payouts[col];
                }
            }
        }

		
}
	
	public static void calc()
	{
		reset();
		multCols();
		sumRows();
		findWinners();
				
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
    			
    			//First Second Third
    			//out.print(Global.tally[i][0]/Math.pow(2.0, Global.numGames-Global.gamesPlayed)*100 + ",");
    			//out.print(Global.tally[i][1]/Math.pow(2.0, Global.numGames-Global.gamesPlayed)*100 + ",");
    			//out.println(Global.tally[i][2]/Math.pow(2.0, Global.numGames-Global.gamesPlayed)*100 + ",");
    			
    			//Total In The Money
    			//out.println((Global.tally[i][0] + Global.tally[i][1] + Global.tally[i][2])/Math.pow(2.0, Global.numGames-Global.gamesPlayed)*100);

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
	
public static void copy(String fromFileName, String toFileName)
					throws IOException 
{
	File fromFile = new File(fromFileName);
	File toFile = new File(toFileName);

	if (!fromFile.exists())
		throw new IOException("FileCopy: " + "no such source file: " + fromFileName);

	if (!fromFile.isFile())
		throw new IOException("FileCopy: " + "can't copy directory: " + fromFileName);
	if (!fromFile.canRead())
		throw new IOException("FileCopy: " + "source file is unreadable: " + fromFileName);

	if (toFile.isDirectory())
		toFile = new File(toFile, fromFile.getName());

	if (toFile.exists()) 
	{
		if (!toFile.canWrite())
			throw new IOException("FileCopy: " + "destination file is unwriteable: " + toFileName);
		System.out.print("Overwrite existing file " + toFile.getName() + "? (Y/N): ");
		System.out.flush();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String response = in.readLine();

		if (!response.equals("Y") && !response.equals("y"))
			throw new IOException("FileCopy: " + "existing file was not overwritten.");
	} 
	else 
	{
		String parent = toFile.getParent();
		if (parent == null)
			parent = System.getProperty("user.dir");
		File dir = new File(parent);
		if (!dir.exists())
			throw new IOException("FileCopy: " + "destination directory doesn't exist: " + parent);
		if (dir.isFile())
			throw new IOException("FileCopy: " + "destination is not a directory: " + parent);
		if (!dir.canWrite())
			throw new IOException("FileCopy: " + "destination directory is unwriteable: " + parent);
	}

	FileInputStream from = null;
	FileOutputStream to = null;

	try 
	{
		from = new FileInputStream(fromFile);
		to = new FileOutputStream(toFile);
		byte[] buffer = new byte[4096];
		int bytesRead;

		while ((bytesRead = from.read(buffer)) != -1)
			to.write(buffer, 0, bytesRead); // write
	} 
	finally 
	{
		if (from != null)
			try 
		{
			from.close();
		} 
		catch (IOException e) 
		{;}
		if (to != null)
			try 
		{
			to.close();
		} 
		catch (IOException e) 
		{;}
	}
}

}
