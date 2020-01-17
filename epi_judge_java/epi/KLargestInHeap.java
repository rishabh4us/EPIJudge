package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.PriorityQueue;
public class KLargestInHeap {

  private static class HeapEntry {
    private Integer index;
    private Integer value;

    public HeapEntry(Integer index, Integer value) {
      this.index = index;
      this.value = value;
    }
  }

  @EpiTest(testDataFile = "k_largest_in_heap.tsv")
  public static List<Integer> kLargestInBinaryHeap(List<Integer> A, int k) {

    if (k <= 0) {
      return Collections.emptyList();
    }
    // create result array
    List<Integer> result = new ArrayList<>();

    // create maxHeap of HeapEntry
    PriorityQueue<HeapEntry> maxHeap = new PriorityQueue<>(A.size(), (o1, o2) -> Integer.compare(o2.value, o1.value));

    // Add first Element to maxHeap
    maxHeap.add(new HeapEntry(0, A.get(0)));

    for (int i = 0; i<k; i++) {
      Integer topIndex = maxHeap.peek().index;
      result.add(maxHeap.remove().value);

      Integer leftChildIndex = 2 * topIndex + 1;
      if (leftChildIndex < A.size()) {
        maxHeap.add(new HeapEntry(leftChildIndex, A.get(leftChildIndex)));
      }

      Integer rightChildIndex = 2 * topIndex + 2;
      if (rightChildIndex < A.size()) {
        maxHeap.add(new HeapEntry(rightChildIndex, A.get(rightChildIndex)));
      }
    }
    return result;
  }
  @EpiTestComparator
  public static BiPredicate<List<Integer>, List<Integer>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KLargestInHeap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}


