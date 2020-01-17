package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SortAlmostSortedArray {

  public static List<Integer>
  sortApproximatelySortedData(Iterator<Integer> sequence, int k) {
    // each number is ka away from its original sorted place.
    // Store k + 1 numbers and want to be able to efficiently extract the minimum number and add a new number

    // Store k numbers
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, (s1, s2) -> Integer.compare(s1, s2));
    for (int i = 0; i < k && sequence.hasNext(); i++) {
      minHeap.add(sequence.next());
    }

    // create result array
    List<Integer> result = new ArrayList<>();

    // Add k + 1 number and extract min and add to result
    while(sequence.hasNext()) {
      minHeap.add(sequence.next());
      Integer head = minHeap.remove();
      result.add(head);
    }

    // sequence is exhausted, now iteratively extract remaining elements and add to result
    result.addAll(Stream.generate(minHeap::remove).limit(minHeap.size()).collect(Collectors.toList()));

    return result;
  }
  @EpiTest(testDataFile = "sort_almost_sorted_array.tsv")
  public static List<Integer>
  sortApproximatelySortedDataWrapper(List<Integer> sequence, int k) {
    return sortApproximatelySortedData(sequence.iterator(), k);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortAlmostSortedArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
