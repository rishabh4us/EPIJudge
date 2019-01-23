package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class NumberOfTraversalsMatrix {
  @EpiTest(testDataFile = "number_of_traversals_matrix.tsv")

  public static int numberOfWays(int n, int m) {
    // TODO - you fill in here.
//    return fullSpaceAlgo(n, m);
    int dp [] = new int [m];
    for(int i = 0; i<m; i++){
      dp[i] = 1;
    }
    for(int i = 1; i<n; i++){
      for(int j = 1; j<m; j++){
        dp[j] = dp[j]  + dp[j-1];
      }}


    return dp[m-1];

  }

  private static int fullSpaceAlgo(int n, int m) {
    int dp [][] = new int [n][m];
    for(int i = 0; i<n; i++){
      dp[i][0] = 1;
    }
    for(int i = 0; i<m; i++){
      dp[0][i] = 1;
    }
    for(int i = 1; i<n; i++){
      for(int j = 1; j<m; j++){
        dp[i][j] = dp[i-1][j]  + dp[i][j-1];
      }}


    return dp[n-1][m-1];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NumberOfTraversalsMatrix.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
