package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsListPalindromic {
  @EpiTest(testDataFile = "is_list_palindromic.tsv")

  public static boolean isLinkedListAPalindrome(ListNode<Integer> L) {

    if (L == null) {
      return true;
    }

    // find the middle of list using fast and slow iterators
    ListNode<Integer> fast = L, slow = L;

    while(fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }

    // reverse second half of linked list
    // create new LinkedList starts
    ListNode<Integer> secondHalfReverseIter = reverseLinkedList(slow);
    ListNode<Integer> firstHalfIter = L;

    // compare the sublists
    while (secondHalfReverseIter != null && firstHalfIter != null) {
      if (secondHalfReverseIter.data != firstHalfIter.data) {
        return false;
      }
      firstHalfIter = firstHalfIter.next;
      secondHalfReverseIter = secondHalfReverseIter.next;
    }

    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsListPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }

  private static ListNode<Integer> reverseLinkedList(ListNode<Integer> head) {
    ListNode<Integer> prev = null, curr = head;
    while (curr != null) {
      ListNode<Integer> temp = curr.next;
      curr.next = prev;
      prev = curr;
      curr = temp;
    }
    return prev;
  }
}
