package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class OnlineMedian {
  public static List<Double> onlineMedian(Iterator<Integer> sequence) {
    int DEFAULT_INITIAL_CAPACITY = 16;
    // create a maxHeap for first half elements
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(DEFAULT_INITIAL_CAPACITY, Collections.reverseOrder());

    // create a minHeap for second half elements
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(DEFAULT_INITIAL_CAPACITY);

    List<Double> onlineMedian = new ArrayList<>();

    while (sequence.hasNext()) {
      // keep adding elements to either heaps
      Integer newElement = sequence.next();
      if (minHeap.isEmpty()) {
        minHeap.add(newElement);
      } else {
        if (newElement >= minHeap.peek()) {
          minHeap.add(newElement);
        } else {
          maxHeap.add(newElement);
        }
      }

      // balance the heaps (right side can contain 1 extra element)
      if (minHeap.size() > maxHeap.size() + 1) {
        maxHeap.add(minHeap.remove());
      } else if (maxHeap.size() > minHeap.size()){
        minHeap.add(maxHeap.remove());
      }

      // compute median
      Double median = minHeap.size() == maxHeap.size() ? 0.5 * (minHeap.peek() + maxHeap.peek()) : Double.valueOf(minHeap.peek());

      // keeping adding to median list
      onlineMedian.add(median);
    }

    //return median List
    return onlineMedian;
  }

  @EpiTest(testDataFile = "online_median.tsv")
  public static List<Double> onlineMedianWrapper(List<Integer> sequence) {
    return onlineMedian(sequence.iterator());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "OnlineMedian.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
