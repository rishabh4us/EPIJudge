package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SnakeString {
  @EpiTest(testDataFile = "snake_string.tsv")

  public static String snakeString(String s) {
    // TODO - you fill in here.

    StringBuffer sb = new StringBuffer();
    for(int i = 1; i<s.length(); i = i+4 ){ sb.append(s.charAt(i)); }
    for(int i = 0; i<s.length(); i = i+2 ){ sb.append(s.charAt(i)); }
    for(int i = 3; i<s.length(); i = i+4 ){ sb.append(s.charAt(i)); }

    return sb.toString();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SnakeString.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
