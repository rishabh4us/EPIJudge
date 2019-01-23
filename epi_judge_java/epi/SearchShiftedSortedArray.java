package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class SearchShiftedSortedArray {
  @EpiTest(testDataFile = "search_shifted_sorted_array.tsv")

  public static int searchSmallest(List<Integer> A) {
    // TODO - you fill in here.
    int left = 0, right = A.size()-1;

    while(left<right){
      int mid = left + ((right-left)/2);

      if(A.get(mid)> A.get(right)){
        left = mid + 1; // ans will me after this element
      } else {
        right = mid; // this element could be mid
      }
    }
    // left == right
    return left;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchShiftedSortedArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
