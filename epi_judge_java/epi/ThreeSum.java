package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
public class ThreeSum {
  @EpiTest(testDataFile = "three_sum.tsv")

  public static boolean hasThreeSum(List<Integer> A, int t) {
    // TODO - you fill in here.

    Collections.sort(A);
    for( int i= 0;i<A.size();i++){
      if(twoSum(A,t-A.get(i),i,A.size()-1))
        return true;
    }

    return false;
  }

  static boolean twoSum (List<Integer> A, int t, int start, int end){
    while(start<=end){
      int sum = A.get(start) + A.get(end);
      if(sum == t) return true;
      else if(sum<t) start++;
      else end--;
    }
    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ThreeSum.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
