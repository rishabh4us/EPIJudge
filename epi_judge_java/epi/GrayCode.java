package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class GrayCode {

  public static List<Integer> grayCode(int numBits) {
    // TODO - you fill in here.
    List<Integer> res = new ArrayList<>(Arrays.asList(0));
    sol(numBits, new HashSet<Integer>(Arrays.asList(0)), res);
    return res;
  }

  private static boolean sol(int n, HashSet<Integer> visited, List<Integer> ans) {
    if(ans.size()== Math.pow(2,n)){
      return differsByOneBit(ans.get(0), ans.get(ans.size()-1));
    }

    for(int i=0; i<n; i++){
      int p = ans.get(ans.size()-1);
      int c = p ^ (1<< i);
      if(! visited.contains(c)){


        ans.add(c);
        visited.add(c);
        if(sol(n,visited,ans))
          return true;
        ans.remove(ans.size()-1);
      }
    }
    return false;
  }

  private static boolean differsByOneBit(int x, int y) {
    int bitDifference = x ^ y;
    return bitDifference != 0 && (bitDifference & (bitDifference - 1)) == 0;
  }

  @EpiTest(testDataFile = "gray_code.tsv")
  public static void grayCodeWrapper(TimedExecutor executor, int numBits)
      throws Exception {
    List<Integer> result = executor.run(() -> grayCode(numBits));

    int expectedSize = (1 << numBits);
    if (result.size() != expectedSize) {
      throw new TestFailure("Length mismatch: expected " +
                            String.valueOf(expectedSize) + ", got " +
                            String.valueOf(result.size()));
    }
    for (int i = 1; i < result.size(); i++)
      if (!differsByOneBit(result.get(i - 1), result.get(i))) {
        if (result.get(i - 1).equals(result.get(i))) {
          throw new TestFailure("Two adjacent entries are equal");
        } else {
          throw new TestFailure(
              "Two adjacent entries differ by more than 1 bit");
        }
      }

    Set<Integer> uniq = new HashSet<>(result);
    if (uniq.size() != result.size()) {
      throw new TestFailure("Not all entries are distinct: found " +
                            String.valueOf(result.size() - uniq.size()) +
                            " duplicates");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "GrayCode.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
