
public class BowlThread implements Runnable
{
	public static int[][] matrix;
	public static int[] results = new int[Global.numGames*2];
	
	public BowlThread(int a, int b)
	{
		matrix = new int[Global.numUsers][2*Global.numGames+1];
		reset();
		
		//Copy each known result to local results
		for (int i = 0; i < (Global.numGames)*2; i++)
		{
			results[i] = Global.results[i];
		}
		
		//Set a and b as our last two game results
		results[2*Global.numGames-4] = a;
		results[2*Global.numGames-3] = 1-a;
		results[2*Global.numGames-2] = b;
		results[2*Global.numGames-1] = 1-b;
	}
	
	public static void reset()
	{
		for (int row = 0; row < Global.numUsers; row++)
			for (int col = 0; col <= 2*Global.numGames; col++)
				matrix[row][col] = Global.picks[row][col];
	}
	
	public void run()
	{
		int total = Global.numGames-Global.gamesPlayed;
		
		
				if(total > 2)
				{
				for (results[44] = 0; results[44] < 2; results[44]++)
    			{
    				results[45] = 1 - results[44];
    				if(total > 3)
    				{
    				for (results[42] = 0; results[42] < 2; results[42]++)
        			{
        				results[43] = 1 - results[42];
        				if(total > 4)
        				{
        				for (results[40] = 0; results[40] < 2; results[40]++)
            			{
            				results[41] = 1 - results[40];
            				if(total > 5)
            				{
            				for (results[38] = 0; results[38] < 2; results[38]++)
                			{
                				results[39] = 1 - results[38];
                				if(total > 6)
                				{
                				for (results[36] = 0; results[36] < 2; results[36]++)
                    			{
                    				results[37] = 1 - results[36];
                    				if(total > 7)
                    				{
                    				for (results[34] = 0; results[34] < 2; results[34]++)
                        			{
                        				results[35] = 1 - results[34];
                        				if(total > 8)
                        				{
                        				for (results[32] = 0; results[32] < 2; results[32]++)
                            			{
                            				results[33] = 1 - results[32];
                            				if(total > 9)
                            				{
                            				for (results[30] = 0; results[30] < 2; results[30]++)
                                			{
                                				results[31] = 1 - results[30];
                                				if(total > 10)
                                				{
                                				for (results[28] = 0; results[28] < 2; results[28]++)
                                    			{
                                    				results[29] = 1 - results[28];
                                    				if(total > 11)
                                    				{
                                    				for (results[26] = 0; results[26] < 2; results[26]++)
                                        			{
                                        				results[27] = 1 - results[26];
                                        				if(total > 12)
                                        				{
                                        				for (results[24] = 0; results[24] < 2; results[24]++)
                                            			{
                                            				results[25] = 1 - results[24];
                                            				if(total > 13)
                                            				{
                                            				for (results[22] = 0; results[22] < 2; results[22]++)
                                                			{
                                                				results[23] = 1 - results[22];
                                                				if(total > 14)
                                                				{
                                                				for (results[20] = 0; results[20] < 2; results[20]++)
                                                    			{
                                                    				results[21] = 1 - results[20];
                                                    				if(total > 15)
                                                    				{
                                                    				for (results[18] = 0; results[18] < 2; results[18]++)
                                                        			{
                                                        				results[19] = 1 - results[18];
                                                        				if(total > 16)
                                                        				{
                                                        				for (results[16] = 0; results[16] < 2; results[16]++)
                                                            			{
                                                            				results[17] = 1 - results[16];
                                                            				if(total > 17)
                                                            				{
                                                            				for (results[14] = 0; results[14] < 2; results[14]++)
                                                                			{
                                                                				results[15] = 1 - results[14];
                                                                				if(total > 18)
                                                                				{
                                                                				for (results[12] = 0; results[12] < 2; results[12]++)
                                                                    			{
                                                                    				results[13] = 1 - results[12];
                                                                    				if(total > 19)
                                                                    				{
                                                                    				for (results[10] = 0; results[10] < 2; results[10]++)
                                                                        			{
                                                                        				results[11] = 1 - results[10];
                                                                        				if(total > 20)
                                                                        				{
                                                                        				for (results[8] = 0; results[8] < 2; results[8]++)
                                                                            			{
                                                                            				results[9] = 1 - results[8];
                                                                            				if(total > 21)
                                                                            				{
                                                                            				for (results[6] = 0; results[6] < 2; results[6]++)
                                                                                			{
                                                                                				results[7] = 1 - results[6];
                                                                                				if(total > 22)
                                                                                				{
                                                                                				for (results[4] = 0; results[4] < 2; results[4]++)
                                                                                    			{
                                                                                    				results[5] = 1 - results[4];
                                                                                    				if(total > 23)
                                                                                    				{
                                                                                    				for (results[2] = 0; results[2] < 2; results[2]++)
                                                                                        			{
                                                                                        				results[3] = 1 - results[2];
                                                                                        				if(total > 24)
                                                                                        				{
                                                                                        				for (results[0] = 0; results[0] < 2; results[0]++)
                                                                                            			{
                                                                                            				results[1] = 1 - results[0];
                                                                                            				calc();
                                                                                            			}
                                                                                        				}
                                                                                        				else
                                                                                        					calc();
                                                                                        			}
                                                                                    				}
                                                                                    				else
                                                                                    					calc();
                                                                                    			}
                                                                                				}
                                                                                				else
                                                                                					calc();
                                                                                			}
                                                                            				}
                                                                            				else
                                                                            					calc();
                                                                            			}
                                                                        				}
                                                                        				else
                                                                        					calc();
                                                                        			}
                                                                    				}
                                                                    				else
                                                                    					calc();
                                                                    			}
                                                                				}
                                                                				else
                                                                					calc();
                                                                			}
                                                            				}
                                                            				else
                                                            					calc();
                                                            			}
                                                        				}
                                                        				else
                                                        					calc();
                                                        			}
                                                    				}
                                                    				else
                                                    					calc();
                                                    			}
                                                				}
                                                				else
                                                					calc();
                                                				
                                                			}
                                            				}
                                            				else
                                            					calc();
                                            				
                                            			}
                                        				}
                                        				else
                                        					calc();
                                        				
                                        			}
                                    				}
                                    				else
                                    					calc();
                                    				
                                    			}
                                				}
                                				else
                                					calc();
                                			}
                            				}
                            				else
                            					calc();
                            			}
                        				}
                        				else
                        					calc();
                        			}
                    				}
                    				else
                    					calc();
                    			}
                				}
                				else
                					calc();
                			}
            				}
            				else
            					calc();
            				
            			}
        				}
        				else
        					calc();
        			}
    				}
    				else
    					calc();
    			}
				}
				else
					calc();
	}
	
	
	public void calc()
	{
		reset();
		multCols();
		sumRows();
		findWinners();
	}
	
	public static void sumRows()
	{
		for (int row = 0; row < Global.numUsers; row++)
		{
			matrix[row][2*Global.numGames] = 0;
			
			for (int col = 0; col < 2*Global.numGames; col++)
			{
				matrix[row][2*Global.numGames] = matrix[row][2*Global.numGames]+
												 matrix[row][col];
			}
		}
			
	}
	
	public static void multCols()
	{		
		for (int row = 0; row < Global.numUsers; row++)
		{
			for (int col = 0; col < 2*Global.numGames; col++)
			{
				matrix[row][col] = matrix[row][col] * results[col];
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
			if (matrix[i][2*Global.numGames]>first)
			{
				numFirst = 1;
				first = matrix[i][2*Global.numGames];
				winners[i][0] = true;
				for (int j = 0; j < i; j++)
					winners[j][0] = false;
			}
			else if (matrix[i][2*Global.numGames]==first)
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
			if (matrix[i][2*Global.numGames]>second && matrix[i][2*Global.numGames]<first)
			{
				numSecond = 1;
				second = matrix[i][2*Global.numGames];
				winners[i][1] = true;
				for (int j = 0; j < i; j++)
					winners[j][1] = false;
			}
			else if (matrix[i][2*Global.numGames]==second)
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
			if (matrix[i][2*Global.numGames]>third && matrix[i][2*Global.numGames]<second)
			{
				numThird = 1;
				third = matrix[i][2*Global.numGames];
				winners[i][2] = true;
				for (int j = 0; j < i; j++)
					winners[j][2] = false;
			}
			else if (matrix[i][2*Global.numGames]==third)
			{
				winners[i][2] = true;
				numThird++;
			}
		}
		}
		
		for (int row = 0; row < Global.numUsers; row++ )
		{
			for (int col = 0; col < 3; col++)
			{
				if (winners[row][col])
				{
					Global.tally[row][col]++;
				}
			}
		}
		
}

}
