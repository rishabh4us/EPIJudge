package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.EpiTestExpectedType;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KClosestStars {
  @EpiUserType(ctorParams = {double.class, double.class, double.class})

  public static class Star implements Comparable<Star> {
    private double x, y, z;

    public Star(double x, double y, double z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }

    public double distance() { return Math.sqrt(x * x + y * y + z * z); }

    @Override
    public int compareTo(Star that) {
      return Double.compare(this.distance(), that.distance());
    }

    @Override
    public String toString() {
      return String.valueOf(distance());
    }
  }

  public static List<Star> findClosestKStars(Iterator<Star> stars, int k) {
    // create a Priority Queue for maxHeap
    PriorityQueue<Star> maxHeap = new PriorityQueue<Star>(k, Collections.reverseOrder());

    // create result array'
    List<Star> result = new ArrayList<>();

    // add K elements to maxHeap
    for (int i = 0; i < k && stars.hasNext(); i++) {
      maxHeap.add(stars.next());
    }

    // keep adding K+1 element and discarding one
    while(stars.hasNext()) {
      maxHeap.add(stars.next());
      maxHeap.poll();
    }

    // what is left in the maxHeap are the k closest starts
    result.addAll(Stream.generate(maxHeap::remove).limit(maxHeap.size()).collect(Collectors.toList()));
    return result;
  }
  @EpiTest(testDataFile = "k_closest_stars.tsv")
  public static List<Star> findClosestKStarsWrapper(List<Star> stars, int k) {
    return findClosestKStars(stars.iterator(), k);
  }

  @EpiTestExpectedType public static List<Double> expectedType;

  @EpiTestComparator
  public static BiPredicate<List<Double>, List<Star>> comp =
      (expected, result) -> {
    if (expected.size() != result.size()) {
      return false;
    }
    Collections.sort(result);
    for (int i = 0; i < result.size(); i++) {
      if (result.get(i).distance() != expected.get(i)) {
        return false;
      }
    }
    return true;
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KClosestStars.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
