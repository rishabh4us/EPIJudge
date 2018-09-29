package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseDigits {
  @EpiTest(testDataFile = "reverse_digits.tsv")
  public static long reverse(int x) {
    // TODO - you fill in here.
    boolean isNegative = x < 0;
    long res = 0;
    x = Math.abs(x);
    while (x > 0) {
      final int digit = x % 10;
      res = res * 10 + digit;
      x = x / 10;
    }
    if (isNegative) res *= -1;
    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseDigits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
