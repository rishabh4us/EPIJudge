package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
public class EnumerateBalancedParentheses {
  @EpiTest(testDataFile = "enumerate_balanced_parentheses.tsv")

  public static List<String> generateBalancedParentheses(int numPairs) {
    // TODO - you fill in here.
    System.out.println("--------------------------------------------");
    List<String> ans = new ArrayList<>();
    sol(numPairs, numPairs, "", ans, numPairs);
    return ans;
  }

  private static void sol(int left, int right, String curr, List<String> ans, int n) {
    System.out.println("left: " + left+" right: " + right + " curr: " + curr +  " ans: " + ans  );
    if(right == 0){
      ans.add(curr);
      return;
    }

    if(left>0){
      sol(left-1, right, curr+"(", ans, n);
    }
    if(left < right){
      sol(left, right-1, curr+")", ans, n);
    }
  }

  @EpiTestComparator
  public static BiPredicate<List<String>, List<String>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EnumerateBalancedParentheses.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
