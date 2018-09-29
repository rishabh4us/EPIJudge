package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class IntAsArrayIncrement {
  @EpiTest(testDataFile = "int_as_array_increment.tsv")
  public static List<Integer> plusOne(List<Integer> A) {
    // TODO - you fill in here.
    List<Integer> ans = new ArrayList<>(A.size() + 1);
    int carry = 0;
    for (int i = A.size() - 1; i >= 0; i--) {
      int newDig = A.get(i) + carry;

      if(i==A.size()-1) newDig += 1;

      carry = newDig / 10;
      ans.add(0, newDig % 10);
    }

    if (carry != 0) {
      ans.add(0, 1);
    }


    return ans;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayIncrement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
