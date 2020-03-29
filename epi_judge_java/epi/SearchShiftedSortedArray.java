package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class SearchShiftedSortedArray {
  @EpiTest(testDataFile = "search_shifted_sorted_array.tsv")

  public static int searchSmallest(List<Integer> A) {
    int left = 0, right = A.size() - 1;

    while (left < right) {
      int mid = left + ((right-left)/2);

      if (A.get(mid) > A.get(right)) {
        // smallest is between mid+1 to right
        left = mid + 1;
      } else if (A.get(mid) < A.get(right)) {
        // smallest cannot be between (mid + 1, right) need to search in left part
        right = mid;
      }
    }
    // loop breaks when left == right
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
