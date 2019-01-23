package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class EvenOddListMerge {
  @EpiTest(testDataFile = "even_odd_list_merge.tsv")

  public static ListNode<Integer> evenOddMerge(ListNode<Integer> L) {
    // TODO - you fill in here.

    ListNode<Integer> eh = new ListNode<>(0, null);
    ListNode<Integer> oh = new ListNode<>(0, null);
    ListNode<Integer> et = eh;
    ListNode<Integer> ot = oh;
    ListNode<Integer> h = L;
    boolean isEven = true;
    while (h != null) {
      if (isEven) {
        et.next = h;
        et = et.next;

      } else {
        ot.next = h;
        ot = ot.next;
      }
      h = h.next;
      isEven = !isEven;
    }
    ot.next = null;
    et.next = oh.next;
    return eh.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvenOddListMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
