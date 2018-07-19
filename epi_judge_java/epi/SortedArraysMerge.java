package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class SortedArraysMerge {

  private static class Entry {
    int val;
    Iterator<Integer> iter;

    Entry(int val, Iterator<Integer> iter) {
      this.val = val;
      this.iter = iter;
    }
  }

  @EpiTest(testDataFile = "sorted_arrays_merge.tsv")
  public static List<Integer> mergeSortedArrays(List<List<Integer>> sortedArrays) {
    // TODO - you fill in here.
    List<Integer> ans = new ArrayList<>();
    List<Iterator<Integer>> iters = new ArrayList<>();
    for (List<Integer> array : sortedArrays) {
      iters.add(array.iterator());
    }

    PriorityQueue<Entry> minHeap = new PriorityQueue<>(sortedArrays.size() , (a, b) -> a.val - b.val);
    for (Iterator<Integer> iter : iters) {
      if (iter.hasNext())
        minHeap.add(new Entry(iter.next(), iter));
    }

    while (!minHeap.isEmpty()) {
      final Entry entry = minHeap.poll();
      ans.add(entry.val);
      if (entry.iter.hasNext())
        minHeap.offer(new Entry(entry.iter.next(), entry.iter));
    }

    return ans;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedArraysMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
