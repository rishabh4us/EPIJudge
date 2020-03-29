package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class SearchFirstKey {
  @EpiTest(testDataFile = "search_first_key.tsv")

  public static int searchFirstOfK(List<Integer> A, int k) {
    int left = 0, right = A.size() - 1, mid = 0, result = -1;

    while (left <= right) {
      mid = left + ((right-left)/2);

      if (k > A.get(mid)) {
        left = mid + 1;
      } else if (k < A.get(mid)) {
        right = mid - 1;
      } else if (A.get(mid) == k) {
        result = mid;
        right = mid - 1;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstKey.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
