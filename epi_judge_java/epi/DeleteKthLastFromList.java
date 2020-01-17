package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class DeleteKthLastFromList {
  @EpiTest(testDataFile = "delete_kth_last_from_list.tsv")

  // Assumes L has at least k nodes, deletes the k-th last node in L.
  public static ListNode<Integer> removeKthLast(ListNode<Integer> L, int k) {
    // create two iterators and one start node
    ListNode<Integer> startNode = new ListNode<>(0, L);
    ListNode<Integer> firstIterator = startNode.next;
    ListNode<Integer> secondIterator = startNode.next;
    // Advance first iterator by k steps
    while(k-- > 0) {
      firstIterator = firstIterator.next;
    }

    // Advance first and second iterator in tandem
    while (firstIterator != null) {
      firstIterator = firstIterator.next;
      secondIterator = secondIterator.next;
    }

    // secondIterator has reached (k+1)th element from last. need to delete its successor

    secondIterator.next = secondIterator.next.next;

    return startNode;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DeleteKthLastFromList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
