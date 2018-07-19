package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
public class StringIntegerInterconversion {

  public static String intToString(int x) {
    // TODO - you fill in here.

    if (x == 0) return "0";
    boolean isNegative = false;
    if (x < 0) {
      isNegative = true;
    }
    StringBuilder sb = new StringBuilder();
    int factor = 10;
    while (x != 0) {
      sb.insert(0, Math.abs(x % 10));
      x = x / factor;
    }
    if (isNegative) sb.insert(0, '-');
    return sb.toString();
  }

  public static int stringToInt(String s) {
    // TODO - you fill in here.
    int ans = 0;
    int factor = 1;
    for (int i = s.length() - 1; i >= 0; i--) {
      if (i == 0 && s.charAt(i) == '-') {
        ans = -1 * ans;
        break;
      }
      ans = ans + (s.charAt(i) - '0') * factor;
      factor *= 10;

    }
    return ans;
  }
  @EpiTest(testDataFile = "string_integer_interconversion.tsv")
  public static void wrapper(int x, String s) throws TestFailure {
    if (!intToString(x).equals(s)) {
      throw new TestFailure("Int to string conversion failed");
    }
    if (stringToInt(s) != x) {
      throw new TestFailure("String to int conversion failed");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
