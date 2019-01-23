package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsNumberPalindromic {
  @EpiTest(testDataFile = "is_number_palindromic.tsv")
  public static boolean isPalindromeNumber(int x) {
    // TODO - you fill in here.
    if (x < 0) return false;
    int numDigits = (int) Math.log10(x) + 1;
    int msd = (int) Math.pow(10, numDigits - 1);
    int lsd = 10;

    while (msd > 1) {
//      System.out.println(" " + x + " msd "+ msd + " lsd "+ lsd + " ");
      if (x / msd != x % lsd) return false;
      x  = x % msd;
      x = x / lsd;
      msd = msd /100;

    }

    return true;


  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsNumberPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
