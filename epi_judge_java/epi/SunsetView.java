package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class SunsetView {
  public static List<Integer>
  examineBuildingsWithSunset(Iterator<Integer> sequence) {
    Deque<BuildingWithHeight> candidates = new ArrayDeque<>();
    int index = 0;
    while(sequence.hasNext()) {
      int nextHeight = sequence.next();
      while (!candidates.isEmpty() && nextHeight >= candidates.peekFirst().height) {
        candidates.removeFirst();
      }
      candidates.addFirst(new BuildingWithHeight(index++, nextHeight));
    }

    return candidates.stream().map(buildingWithHeight -> buildingWithHeight.buildingId).collect(Collectors.toList());
  }

  private static class BuildingWithHeight {
    int buildingId;
    int height;

    public BuildingWithHeight(int buildingId, int height) {
      this.buildingId = buildingId;
      this.height = height;
    }
  }

  @EpiTest(testDataFile = "sunset_view.tsv")
  public static List<Integer>
  examineBuildingsWithSunsetWrapper(List<Integer> sequence) {
    return examineBuildingsWithSunset(sequence.iterator());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SunsetView.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
