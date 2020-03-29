package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.Set;
import java.util.HashSet;

public class IsStringPermutableToPalindrome {
  @EpiTest(testDataFile = "is_string_permutable_to_palindrome.tsv")

  public static boolean canFormPalindrome(String s) {
    Set<Character> charsWithOddFrequencies = new HashSet<>();

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      if (charsWithOddFrequencies.contains(c)) {
        // c has now appeared even number of times
        charsWithOddFrequencies.remove(c);
      } else {
        // c has now appeared an odd number of times
        charsWithOddFrequencies.add(c);
      }
    }

    return charsWithOddFrequencies.size() <= 1;

  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPermutableToPalindrome.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
