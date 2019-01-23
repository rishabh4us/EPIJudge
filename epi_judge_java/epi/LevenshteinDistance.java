package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class LevenshteinDistance {
  @EpiTest(testDataFile = "levenshtein_distance.tsv")

  public static int levenshteinDistance(String A, String B) {
    // TODO - you fill in here.
    int dp[][] = new int[A.length() + 1][B.length() + 1];

    for (int row = 1; row <= A.length(); row++) {
      dp[row][0] = row;
    }
    for (int col = 1; col <= B.length(); col++) {
      dp[0][col] = col;
    }
    for (int row = 1; row <= A.length(); row++) {
      for (int col = 1; col <= B.length(); col++) {
        if (A.charAt(row - 1) == B.charAt(col - 1))
          dp[row][col] = dp[row - 1][col - 1];
        else
          dp[row][col] = 1+ Math.min(dp[row - 1][col - 1], Math.min(dp[row][col - 1], dp[row - 1][col]));

      }
//      print(dp);
    }

    return dp[A.length()][B.length()];
  }

  private static void print(int[][] dp) {
    System.out.println("---------");
    for (int row = 0; row < dp.length; row++) {
      for (int col = 0; col < dp[0].length; col++) {
        System.out.print(dp[row][col] + ", ");
      }
      System.out.println();
    }
    System.out.println("---------");
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LevenshteinDistance.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
