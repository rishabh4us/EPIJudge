package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
public class ReverseWords {

  public static void reverseWords(char[] input) {
    int n = input.length;

    // reverse entire input
    reverse(input, 0, n-1);

    // reverse each individual word in the input string
    int start = 0, end = 0;

    while (start < n) {

      while (start < end || (start < n && input[start] == ' ' )) {
        ++start; // skip space characters
      }

      while ( end < start || (end < n && input[end] != ' ')) {
        ++end;
      }

      reverse(input, start, end-1);
    }


    return;
  }

  private static void reverse(char[] array, int start, int end) {
    while (start < end) {
      char temp = array[start];
      array[start++] = array[end];
      array[end--] = temp;
    }

  }

  @EpiTest(testDataFile = "reverse_words.tsv")
  public static String reverseWordsWrapper(TimedExecutor executor, String s)
      throws Exception {
    char[] sCopy = s.toCharArray();

    executor.run(() -> reverseWords(sCopy));

    return String.valueOf(sCopy);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseWords.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
