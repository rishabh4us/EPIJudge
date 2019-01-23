package epi;
import com.sun.tools.javac.util.Pair;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class LongestNondecreasingSubsequence {
  @EpiTest(testDataFile = "longest_nondecreasing_subsequence.tsv")

  public static int longestNondecreasingSubsequenceLength(List<Integer> A) {
    // TODO - you fill in here.
Integer [] dp = new Integer[A.size()];
Arrays.fill(dp,1);
//dp[0] = 1;
    for(int i=1; i< A.size(); i++){
      for(int j=0; j<i; j++){
        if(A.get(j) <= A.get(i)){
          dp[i] = Math.max(dp[j]+1, dp[i]);
        }


      }

    }

    printLargest(A,dp);

    return Collections.max(Arrays.asList(dp));

  }

  private static void printLargest(List<Integer> A, Integer[] dp) {
    Pair<Integer, Integer> mxpair = getMaxItem(Arrays.asList(dp));

    int length = mxpair.fst;
    int index = mxpair.snd;

    int curr = index-1 ;
    List<Integer> ans = new ArrayList<>();
    ans.add(A.get(index));
    while(curr >=0){
      if(A.get(curr) <= A.get(index) && dp[index]-dp[curr]==1){
        ans.add(A.get(curr));
        index = curr;
      }
      curr --;
    }
    System.out.println(A);
    System.out.println(mxpair.fst);
    System.out.println(ans);
  }

  private static Pair<Integer, Integer> getMaxItem(List<Integer> A) {
    Pair<Integer, Integer> ans = null;
    int max = A.get(0);
    int maxi = 0;
    for(int i=0; i< A.size(); i++){
      if(max < A.get(i)){
        max = A.get(i);
        maxi = i;
      }
    }
    ans = new Pair<>(max, maxi);
    return ans;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LongestNondecreasingSubsequence.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
