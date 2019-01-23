package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
public class KthLargestInArray {
  // The numbering starts from one, i.e., if A = [3,1,-1,2] then
  // findKthLargest(A, 1) returns 3, findKthLargest(A, 2) returns 2,
  // findKthLargest(A, 3) returns 1, and findKthLargest(A, 4) returns -1.
  @EpiTest(testDataFile = "kth_largest_in_array.tsv")
  public static int findKthLargest(int kth, List<Integer> A) {
    int k = kth - 1; // zero indexing
    if (k >= A.size()) throw new IllegalArgumentException("cannot be more than size");

    int start = 0, end = A.size() - 1;
    while (start <= end) {

      //eg.  5 - 3 +1= 3
      int pivot = ((int) Math.random() * (end - start + 1)) + start;
      int newPivot = rearrange(A, pivot, start, end);
      if (newPivot == k) {
        return A.get(k);
      } else if (newPivot < k) {
        start = newPivot + 1;
      } else {
        end = newPivot - 1;
      }
    }
    return -1;

  }


  private static int rearrange(List<Integer> ip, int pivot, int start, int end) {
    // rearrange pivot index to correct position and move elements around it.

    int pivotVal = ip.get(pivot);
    Collections.swap(ip, pivot, end);
    int finPos = start;

    for (int i = start; i < end; i++) {
      if (ip.get(i) > pivotVal)
        Collections.swap(ip, i, finPos++);
    }
    Collections.swap(ip, finPos, end);

    return finPos;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KthLargestInArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
