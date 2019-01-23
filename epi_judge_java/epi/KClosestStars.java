package epi;

import epi.test_framework.*;

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
    // TODO - you fill in here.

//    PriorityQueue<Star> maxheap = new PriorityQueue<Star>(k + 1, ((o1, o2) -> Double.compare(o2.distance(), o1.distance())));

    PriorityQueue<Star> maxheap = new PriorityQueue<Star>(k + 1, Collections.reverseOrder());

    int counter = 0;
    while (counter++ < k) {
      if (stars.hasNext())
        maxheap.offer(stars.next());
    }
    while (stars.hasNext()) {
      Star star = stars.next();
      if (star.distance() < maxheap.peek().distance()) {
      maxheap.offer(star);
      maxheap.remove();
      }
    }
    return new ArrayList<>(maxheap);
//    return Stream.generate(maxheap::remove).limit(maxheap.size()).collect(Collectors.toList());
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
