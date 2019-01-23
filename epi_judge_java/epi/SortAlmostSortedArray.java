package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class SortAlmostSortedArray {

  public static List<Integer>
  sortApproximatelySortedData(Iterator<Integer> sequence, int k) {
    // TODO - you fill in here.

    List<Integer> ans = new ArrayList<>(k+1);

    PriorityQueue<Integer> minHeap = new PriorityQueue<>(k+1);
    int i=0;
    while(sequence.hasNext() && i < k){
      minHeap.add(sequence.next());
    }

    while(sequence.hasNext()){
      minHeap.add(sequence.next()); //start to add k+1
      ans.add(minHeap.remove());
    }

    while(!minHeap.isEmpty()){
      ans.add(minHeap.remove());
    }
    return ans;
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
