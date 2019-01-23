package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashSet;

public class IsStringPermutableToPalindrome {
  @EpiTest(testDataFile = "is_string_permutable_to_palindrome.tsv")

  public static boolean canFormPalindrome(String s) {
    // TODO - you fill in here.

    HashSet<Character> hs = new HashSet<>();
    for(Character c : s.toCharArray()){
      if(hs.contains(c)) hs.remove(c);
      else hs.add(c);
    }
    return hs.size()<=1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPermutableToPalindrome.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
