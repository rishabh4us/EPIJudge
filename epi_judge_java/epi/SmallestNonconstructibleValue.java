package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
public class SmallestNonconstructibleValue {
  @EpiTest(testDataFile = "smallest_nonconstructible_value.tsv")

  public static int smallestNonconstructibleValue(List<Integer> A) {
    Collections.sort(A);

    int maxValueConstructible = 0;
    for (int i = 0; i < A.size(); i++) {
      if (A.get(i) > maxValueConstructible + 1) {
        break;
      }
      maxValueConstructible += A.get(i);
    }

    return maxValueConstructible + 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SmallestNonconstructibleValue.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
