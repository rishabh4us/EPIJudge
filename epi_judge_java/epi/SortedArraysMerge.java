package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class SortedArraysMerge {
  @EpiTest(testDataFile = "sorted_arrays_merge.tsv")

  public static List<Integer>
  mergeSortedArrays(List<List<Integer>> sortedArrays) {

    List<Integer> result = new ArrayList<Integer>();
    int k = sortedArrays.size();

    // create an iterator array for each of the sorted arrays
    List<Iterator<Integer>> arrayIters = new ArrayList<>();
    for (List<Integer> sampleArray : sortedArrays) {
      arrayIters.add(sampleArray.iterator());
    }

    PriorityQueue<ArrayElement> minHeap = new PriorityQueue<>(k, (s1, s2) -> Integer.compare(s1.value, s2.value));

    // initialize a minHeap from first elements of each of sorted arrays
    for (int i = 0; i < k; i++) {
      if (arrayIters.get(i).hasNext()) {
        minHeap.add(new ArrayElement(arrayIters.get(i).next(), i));
      }
    }

    // then remove element from minHeap and add to result array
    // then take the next element from the array to which the removed element belongs
    // and add to minHeap
    // keep doing this till all elements in all arrays are not put in minHeap
    while(!minHeap.isEmpty()) {
      ArrayElement min = minHeap.poll();

      result.add(min.value);

      int arrayNumber = min.arrayId;
      if (arrayIters.get(arrayNumber).hasNext()) {
        minHeap.add(new ArrayElement(arrayIters.get(arrayNumber).next(), arrayNumber));
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedArraysMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

class ArrayElement {

  Integer value;
  int arrayId;

  public ArrayElement(Integer value, int arrayId) {
    this.value = value;
    this.arrayId = arrayId;
  }
}
