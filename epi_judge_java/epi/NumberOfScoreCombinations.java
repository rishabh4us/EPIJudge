package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class NumberOfScoreCombinations {
  @EpiTest(testDataFile = "number_of_score_combinations.tsv")

  public static int
  numCombinationsForFinalScore(int finalScore,
                               List<Integer> individualPlayScores) {
    // TODO - you fill in here.

    int dp[][] = new int[individualPlayScores.size() + 1][finalScore + 1];
    for (int i = 0; i < dp.length; i++) {
      dp[i][0] = 1;
    }
    for (int row = 1; row < dp.length; row++) {
      for (int col = 1; col <= finalScore; col++) {
        int val2 = col - individualPlayScores.get(row - 1) >= 0 ? dp[row][col - individualPlayScores.get(row - 1)] : 0;
        dp[row][col] = dp[row - 1][col] + val2;
      }

    }


    return dp[individualPlayScores.size()][finalScore];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NumberOfScoreCombinations.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
