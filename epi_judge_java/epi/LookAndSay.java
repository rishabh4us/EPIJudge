package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class LookAndSay {
  @EpiTest(testDataFile = "look_and_say.tsv")

  public static String lookAndSay(int n) {
    // TODO - you fill in here.
    String s  = "1";
    for(int i = 1; i<n; i++){
      s = next(s);
    }
    return s;
  }

  private static String next(String s) {
    String ans = "";

    int i = 0;
    int[] ce = new int[2];
    while (i < s.length()) {
      int j = i;
      ce[0] = 0;
      while (j < s.length() && s.charAt(j) == s.charAt(i)) {
        ce[0]++;
        j++;
      }
      ce[1] = s.charAt(i);

      ans += "" + ce[0] + (char)ce[1];
      i = j;
    }
    return ans;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LookAndSay.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
