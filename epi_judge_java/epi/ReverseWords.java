package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
public class ReverseWords {

  public static void reverseWords(char[] input) {
    // TODO - you fill in here.
    // reverse entire string
    int i=0,j=input.length-1;
    reverse(input, i, j);

    // reverse each work
    i=0; j=0;
    while(i<input.length){
      while (j<input.length && input[j] != ' ' ) j++; // j will skip all characters
      while(i<input.length && input[i] == ' ') i++; // i will skip all spaces
      reverse(input, i,j-1);
      i=j;
      j++;
    }

    return;
  }

  private static void reverse(char[] input, int i, int j) {
    while(i<=j){
      swap(input, i++, j--);
    }
  }

  private static void swap(char[] input, int i, int j) {
    char t = input[i];
    input[i] = input[j];
    input[j] = t;
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
