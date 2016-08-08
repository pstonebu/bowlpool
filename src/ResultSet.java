import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResultSet {

    List<Result> results = new ArrayList<Result>();

    public ResultSet(List<Result> results) {
        this.results = results;
    }

    public ResultSet() {
    }

    public ResultSet(ResultSet resultSet) {
        for (Result result : resultSet.results) {
            results.add(new Result(result));
        }
    }

    public List<Result> getResults() {
        /*return results.stream()
                .sorted(Comparator.comparing(Result::getGameNumber))
                .collect(Collectors.toList());*/
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public void addResult(Result result) {
        if (results.contains(result)) {
            results.remove(results.indexOf(result));
        }
        results.add(result);
    }

    public boolean removeResult(Result result) {
        if (results.contains(result)) {
            results.remove(results.indexOf(result));
            return true;
        }
        return false;
    }

    public void addResults(List<Result> results) {
        for (Result result : results) {
            addResult(new Result(result));
        }
    }

    public void mergeResultSets(ResultSet resultSet) {
        for (Result result : resultSet.getResults()) {
            addResult(new Result(result));
        }
    }

    public Result getResult(int gameNumber) {
        return (results.stream()
                .filter(t -> t.getGameNumber() == gameNumber)
                .collect(Collectors.toList())).get(0);
    }
}
