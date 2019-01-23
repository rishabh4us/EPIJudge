package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class SmallestSubarrayCoveringAllValues {

  public static class Subarray {
    // Represent subarray by starting and ending indices, inclusive.
    public Integer start;
    public Integer end;

    public Subarray(Integer start, Integer end) {
      this.start = start;
      this.end = end;
    }
  }

  public static Subarray
  findSmallestSequentiallyCoveringSubset(List<String> paragraph,
                                         List<String> keywords) {

    // https://leetcode.com/articles/minimum-window-substring/

    Subarray sol = null;
    Map<String, Integer> keywordsMap = putInMap(keywords);

    Map<String, Integer> paraMap = new HashMap<>();
    int left = 0, right = 0;
    int minLen = paragraph.size() + 1;
    int covered = 0;

    while (right < paragraph.size()) {
      System.out.println("right at: " + right);
      // add to dict --> check against keywordsDict -->
      // if words in paragraph count in another hash map if key in keywordsDict -->
      // reduce window size

      String curr = paragraph.get(right);

      if (keywords.contains(curr)) {
        paraMap.put(curr, paraMap.getOrDefault(curr, 0) + 1);
        if (paraMap.get(curr) <= keywordsMap.get(curr)) covered++;
      }

      if (covered >= keywords.size()) {

        // if covered == all keywords

        while (left <= right && covered >= keywords.size()) {

          System.out.println("left at: " + left);

          String lWord = paragraph.get(left);
          if (keywordsMap.containsKey(lWord)) {
            paraMap.put(lWord, paraMap.get(lWord) - 1);
            if (paraMap.get(lWord) < keywordsMap.get(lWord)) {
              covered--;
            }
          }

          int windowLength = right - left + 1;
          minLen = minLen > windowLength ? windowLength : minLen;
          if(minLen==windowLength) sol = new Subarray(left, right);
          left++;
        }

      }

      right++;

    }

    // TODO - you fill in here.
    System.out.println("solution at: " + sol.start + " "+ sol.end);
    List<String> xx = new ArrayList<>();
    for (int i = sol.start; i<=sol.end; i++) {
      xx.add(paragraph.get(i));
    }

    print(keywordsMap);
    print(putInMap(xx));



    return sol;
  }

  private static void print(Map<String, Integer> keywordsMap) {
    System.out.println("Map");
    for (Map.Entry<String, Integer> entry : keywordsMap.entrySet()) {
      System.out.println(entry.getKey()+" : "+entry.getValue());
    }
  }

  private static Map<String, Integer> putInMap(List<String> keywords) {
    Map<String, Integer> keywordsMap = new HashMap<>();
    for (String key : keywords) {
      keywordsMap.put(key, keywordsMap.getOrDefault(key,0) +1);
    }
    return keywordsMap;
  }

  @EpiTest(testDataFile = "smallest_subarray_covering_all_values.tsv")
  public static int findSmallestSequentiallyCoveringSubsetWrapper(
      TimedExecutor executor, List<String> paragraph, List<String> keywords)
      throws Exception {
    Subarray result = executor.run(
        () -> findSmallestSequentiallyCoveringSubset(paragraph, keywords));

    int kwIdx = 0;
    if (result.start < 0) {
      throw new TestFailure("Subarray start index is negative");
    }
    int paraIdx = result.start;

    while (kwIdx < keywords.size()) {
      if (paraIdx >= paragraph.size()) {
        throw new TestFailure("Not all keywords are in the generated subarray");
      }
      if (paraIdx >= paragraph.size()) {
        throw new TestFailure("Subarray end index exceeds array size");
      }
      if (paragraph.get(paraIdx).equals(keywords.get(kwIdx))) {
        kwIdx++;
      }
      paraIdx++;
    }
    return result.end - result.start + 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SmallestSubarrayCoveringAllValues.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
