import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: patrick.stoneburner
 * Date: 12/28/12
 * Time: 3:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class zipper
{
    public static void output(String current, String favorite, String underdog, String filename)
    {
        Scanner scan1 = null;
        Scanner scan2 = null;
        Scanner scan3 = null;


        try
        {
            PrintWriter out = new PrintWriter(new FileWriter(filename));
            scan1 = new Scanner (new File (current));
            scan2 = new Scanner (new File (favorite));
            scan3 = new Scanner (new File (underdog));

            for (int i = 0; i < Global.numUsers; i++)
            {
                String first = scan1.nextLine();
                String second = scan2.nextLine().split(",")[3];
                String third = scan3.nextLine().split(",")[3];
                out.println(first + "," + second + "," + third);
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
