package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class IntAsArrayMultiply {
  @EpiTest(testDataFile = "int_as_array_multiply.tsv")
  public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
    // TODO - you fill in here.
if(num1.size() ==1 && num1.get(0) == 0){
  ArrayList<Integer> a = new ArrayList<>();
  a.add(0);
  return a;
};
    boolean isNeg = num1.get(0) * num2.get(0) < 0;

    num1.set(0, Math.abs(num1.get(0)));
    num2.set(0, Math.abs(num2.get(0)));

    List<Integer> ans = new ArrayList<>(num1.size() + num2.size());

    for (int i = num1.size() + num2.size() + -1; i >= 0; i--) {
      ans.add(0);
    }
    for (int i = num1.size() - 1; i >= 0; i--) {
      for (int j = num2.size() - 1; j >= 0; j--) {
        ans.set(i + j + 1, ans.get(i + j + 1) + num1.get(i) * num2.get(j));
        ans.set(i + j, ans.get(i + j + 1) / 10 + ans.get(i + j));
        ans.set(i + j + 1, ans.get(i + j + 1) % 10);
      }
    }
    ans.set(0, isNeg ? -1 * ans.get(0) : ans.get(0));

    // remove 0 from beginning


    return ans;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayMultiply.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
