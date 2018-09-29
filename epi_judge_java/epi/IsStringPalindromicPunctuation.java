package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsStringPalindromicPunctuation {
  @EpiTest(testDataFile = "is_string_palindromic_punctuation.tsv")

  public static boolean isPalindrome(String s) {
    // TODO - you fill in here.

    int i = 0, j = s.length() - 1;
    while (i <= j) {
      if (!Character.isLetterOrDigit(s.charAt(i))) i++;
      else if(!Character.isLetterOrDigit(s.charAt(j))) j--;
      else{
        if (!(s.charAt(i++) + "").equalsIgnoreCase(s.charAt(j--) + "")) return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPalindromicPunctuation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
