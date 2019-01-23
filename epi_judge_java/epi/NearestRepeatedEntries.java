package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NearestRepeatedEntries {
  @EpiTest(testDataFile = "nearest_repeated_entries.tsv")

  public static int findNearestRepetition(List<String> paragraph) {
    // TODO - you fill in here.

    Map<String, Integer> hm = new HashMap<>();
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < paragraph.size(); i++) {
      String s = paragraph.get(i);
      if (hm.containsKey(s)) {
        int diff = i - hm.get(s);
        min = min > diff ? diff : min;

      }
      hm.put(s, i);

    }
    return min == Integer.MAX_VALUE ? -1 : min;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NearestRepeatedEntries.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
