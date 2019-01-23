package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class IntersectSortedArrays {
  @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")

  public static List<Integer> intersectTwoSortedArrays(List<Integer> A,
                                                       List<Integer> B) {
    // TODO - you fill in here.
    // nlogm if loop in 1 AND binary search in another
return solution(A,B);
    // n+M if loop in both

//    List<Integer> result = new ArrayList<>();
//    int one = 0, two = 0;
//    while (one < A.size() && two < B.size()) {
//
//      // EXTREMELY TO  USE  .EQUALS() AND not == !!!!!!!!!!!
//      if (A.get(one).equals(B.get(two))) {
//        // don't add element if its already added
//        if (result.isEmpty() || !result.get(result.size()-1).equals( A.get(one)) ) {
//          result.add(A.get(one));
//
//        }
//        one++;
//        two++;
//      } else if (A.get(one) < B.get(two)) {
//        one++;
//      } else {
//        two++;
//      }
//
//    }
//    return result;
  }

  private static List<Integer> solution(List<Integer> a, List<Integer> b) {
    List<Integer> smaller = a.size() < b.size() ? a : b;
    List<Integer> larger = a.size() < b.size() ? b : a;
    List<Integer> result = new ArrayList<>();

    for (int i = 0; i < smaller.size(); i++) {
      int ele = smaller.get(i);
        if ((i == 0|| !smaller.get(i - 1).equals(ele)) && findBinarySearch(ele, larger))
          result.add(ele);
    }
    return result;
  }

  private static boolean findBinarySearch(int ele, List<Integer> ip) {

    int start = 0, end = ip.size() - 1;
    while (start <= end) {
      int mid = (end - start) / 2 + start;

      if (ip.get(mid).equals(ele)) return true;
      else if (ip.get(mid) < ele) start = mid + 1;
      else end = mid - 1;

    }
    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntersectSortedArrays.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
