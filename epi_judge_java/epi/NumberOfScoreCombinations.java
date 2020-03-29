package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOfScoreCombinations {
  @EpiTest(testDataFile = "number_of_score_combinations.tsv")

  public static int
  numCombinationsForFinalScore(int finalScore,
                               List<Integer> individualPlayScores) {
    Map<String, Integer> numScoreCombinationsMap = new HashMap<>();
    int ans = numCombinationsForFinalScoreHelper(finalScore, individualPlayScores, 0, numScoreCombinationsMap);
    return ans;
  }

  private static int numCombinationsForFinalScoreHelper(int finalScore, List<Integer> individualScores, int current, Map<String, Integer> numScoreCombinationsMap) {

    //base case when target finalScore is 0
    if (finalScore == 0) {
      return 1;
    }

    //base case when target finalScore is negative
    if (finalScore < 0) {
      return 0;
    }

    //base case when current index is out of bounds
    if (current >= individualScores.size()) {
      return 0;
    }
    if (numScoreCombinationsMap.containsKey(finalScore + "_" + current)) {
      return numScoreCombinationsMap.get(finalScore + "_" + current);
    }

    //processing calculate left
    int withPlaySum = numCombinationsForFinalScoreHelper(finalScore - individualScores.get(current), individualScores, current, numScoreCombinationsMap);

    //processing calculate right
    int withoutPlaySum = numCombinationsForFinalScoreHelper(finalScore, individualScores, current+1, numScoreCombinationsMap);

    //combine left and right result
    int ans = withPlaySum + withoutPlaySum;
    numScoreCombinationsMap.put(finalScore + "_" + current, ans);

    //return answer
    return ans;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NumberOfScoreCombinations.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
