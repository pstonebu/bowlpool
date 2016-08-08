import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.*;

public class Main {
    public static int numGames = 0;
    public static int numUsers = 0;
    public static int gamesPlayed = 0;
    public static boolean lastGame4Teams = true;
    public static List<PickSet> pickList = new ArrayList<PickSet>();
    public static ResultSet resultSet = new ResultSet();
    public static String picksFile = "picks.txt";
    public static String resultsFile = "results.txt";
    public static String configFile = "config.xml";
    public static List<Selection> eliminated = new ArrayList<Selection>();
    public static List<Selection> eligible = new ArrayList<Selection>();

	public static void main(String[] args) {

        long start = System.currentTimeMillis();
        numGames = countLines(resultsFile);
        numUsers = countLines(picksFile);
        resultSet = scanResults();
        pickList = scanPicks();

        try {
            File fXmlFile = new File(configFile);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = null;
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList options = doc.getDocumentElement().getChildNodes();

            for (int i = 0;i < options.getLength(); i++) {

                Node nNode = options.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String optionName = eElement.getElementsByTagName("name").item(0).getTextContent();
                    String optionValue = eElement.getElementsByTagName("value").item(0).getTextContent();

                    if (optionName.equals("lastGame4Teams")) {
                        lastGame4Teams = Boolean.valueOf(optionValue);
                    } else if (optionName.equals("eliminatedTeams")) {
                        for (int j = 0; j < optionValue.split(",").length; j++) {
                            eliminated.add(Simulation.getSelection(Integer.valueOf(optionValue.split(",")[j])));
                        }
                    }
                }

            }
        } catch (Exception ex) {
            //Do nothing, fall back to defaults
        }

        for (int i = 0; i < 4; i++) {
            if (!eliminated.contains(Simulation.getSelection(i))) {
                eligible.add(Simulation.getSelection(i));
            }
        }

        //Set eliminated here
        //eliminated.add(Selection.FAVORITE);
        //eliminated.add(Selection.UNDERDOG);
        //eliminated.add(Selection.EXTRA1);
        //eliminated.add(Selection.EXTRA2);

        System.out.println("Init took " + (System.currentTimeMillis() - start) + " ms.");

        Simulation current = new Simulation();
        current.run();
        int power = gamesPlayed == numGames ? 1 : (numGames - gamesPlayed - 1);
        double iterations = Math.pow(2.0D, power) * (lastGame4Teams ? (4-eliminated.size()) : 2.0D);
        Map<PickSet,Double> currentAverage = new HashMap<PickSet,Double>();
        for (PickSet pickSet : current.getPayout().keySet()) {
            currentAverage.put(pickSet, current.getPayout().get(pickSet)/ iterations);
        }

        iterations = iterations / 2.0D;
        current.simulateNextGame(gamesPlayed != numGames ? Selection.FAVORITE : eligible.get(0));
        Map<PickSet,Double> favoriteAverage = new HashMap<PickSet,Double>();
        for (PickSet pickSet : current.getPayout().keySet()) {
            favoriteAverage.put(pickSet, current.getPayout().get(pickSet)/ iterations);
        }

        current.simulateNextGame(gamesPlayed != numGames ? Selection.UNDERDOG : eligible.get(1));
        Map<PickSet,Double> underdogAverage = new HashMap<PickSet,Double>();
        for (PickSet pickSet : current.getPayout().keySet()) {
            underdogAverage.put(pickSet, current.getPayout().get(pickSet)/ iterations);
        }

        outputUpdate(currentAverage, favoriteAverage, underdogAverage);

        long finish = System.currentTimeMillis();
        System.out.println("Took " + (finish-start) + " ms.");
    }

    public static int countLines(String filename) {
        Scanner scan;
        int count = 0;

        try {
            scan = new Scanner(new File(filename));
            while (scan.hasNextLine()) {
                count++;
                scan.nextLine();
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.exit(0);
        }

        return count;
    }

    public static List<PickSet> scanPicks() {
        Scanner scan = null;
        List<PickSet> pickList = new ArrayList<PickSet>();
        try {
            scan = new Scanner (new File (picksFile));

            for (int i = 0; i < numUsers; i++)
            {
                String[] current = scan.nextLine().split("[ \\t]+");
                PickSet pickSet = new PickSet();
                pickSet.setPlayerName(current[0]);

                for (int gameNumber = 0; gameNumber < numGames; gameNumber++)
                {
                    Pick pick = new Pick();
                    pick.setGameNumber(gameNumber);
                    if (gameNumber+1 == numGames && lastGame4Teams) {
                        int favoriteWeight = Integer.valueOf(current[(gameNumber) * 2 + 1]);
                        int underdogWeight = Integer.valueOf(current[(gameNumber) * 2 + 2]);
                        int extra1Weight = Integer.valueOf(current[(gameNumber) * 2 + 3]);
                        int extra2Weight = Integer.valueOf(current[(gameNumber) * 2 + 4]);
                        if (favoriteWeight > 0) {
                            pick.setSelection(Selection.FAVORITE);
                        } else if (underdogWeight > 0) {
                            pick.setSelection(Selection.UNDERDOG);
                        } else if (extra1Weight > 0) {
                            pick.setSelection(Selection.EXTRA1);
                        } else if (extra2Weight > 0) {
                            pick.setSelection(Selection.EXTRA2);
                        }
                        pick.setWeight(favoriteWeight + underdogWeight + extra1Weight + extra2Weight);
                        pickSet.addPick(pick);
                    } else {
                        int favoriteWeight = Integer.valueOf(current[(gameNumber) * 2 + 1]);
                        int underdogWeight = Integer.valueOf(current[(gameNumber) * 2 + 2]);
                        pick.setWeight(favoriteWeight + underdogWeight);
                        pick.setSelection(favoriteWeight > 0 ? Selection.FAVORITE : Selection.UNDERDOG);
                        pickSet.addPick(pick);
                    }
                }

                pickSet.setPoints(resultSet);
                pickList.add(pickSet);
            }
        } catch (FileNotFoundException e) {
            System.exit(0);
        }

        return pickList;
    }

    public static ResultSet scanResults() {
        Scanner scan = null;
        ResultSet resultSet = new ResultSet();
        try {
            scan = new Scanner(new File("results.txt"));

            while (scan.hasNext()) {
                String[] current = scan.nextLine().split("[ \\t]+");
                int favorite = 0;
                int underdog = 0;
                int extra1 = 0;
                int extra2 = 0;
                if (current.length == 4) {
                    favorite = Integer.valueOf(current[1]);
                    underdog = Integer.valueOf(current[2]);
                } else if (lastGame4Teams && current.length == 8) {
                    favorite = Integer.valueOf(current[2]);
                    underdog = Integer.valueOf(current[3]);
                    extra1 = Integer.valueOf(current[4]);
                    extra2 = Integer.valueOf(current[5]);
                }

                Result result = new Result();
                result.setGameNumber(gamesPlayed);
                if (favorite == 1 || underdog == 1) {
                    result.setCorrectSelection(favorite == 1 ? Selection.FAVORITE : Selection.UNDERDOG);
                    gamesPlayed++;
                } else if (extra1 == 1 || extra2 == 1) {
                    result.setCorrectSelection(extra1 == 1 ? Selection.EXTRA1 : Selection.EXTRA2);
                    gamesPlayed++;
                } else {
                    break;
                }

                if (result.getCorrectSelection() != null) {
                    resultSet.addResult(result);
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            return null;
        }

        return resultSet;
    }

    public static void outputUpdate(Map<PickSet,Double> current, Map<PickSet,Double> favorite, Map<PickSet,Double> underdog) {
        try {
            PrintWriter out = new PrintWriter(new FileWriter("update2.txt"));

            for (PickSet pickSet : current.keySet())
            {
                String name = "\"" + pickSet.getPlayerName() + "\"";
                int currentScore = pickSet.getCurrentScore();
                int potential = pickSet.getPotential();
                double value = current.get(pickSet);
                double valueIfFav = favorite.get(pickSet);
                double valueIfUnd = underdog.get(pickSet);
                out.println(name + "," + currentScore + "," + potential + "," + value + "," + valueIfFav + "," + valueIfUnd);
            }

            out.flush();
            out.close();
        } catch (IOException e) {
            System.exit(0);
        }

    }
}
