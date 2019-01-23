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

    Map<String, Integer> workIndexMap = new HashMap<>();
    int min_distance = Integer.MAX_VALUE;
    boolean found = false;
    for (int i = 0; i < paragraph.size(); i++) {
      String w = paragraph.get(i);
      if (workIndexMap.containsKey(w)) {
        final int dist = i - workIndexMap.get(w);
        if (min_distance >= dist) {
          min_distance = dist;
          found = true;
        }
      }

      workIndexMap.put(w, i);
    }
    return found ? min_distance : -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NearestRepeatedEntries.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
