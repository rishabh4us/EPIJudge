package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SortList {
  @EpiTest(testDataFile = "sort_list.tsv")

  public static ListNode<Integer> stableSortList(ListNode<Integer> L) {
    // TODO - you fill in here.
    if(L == null || L.next == null) return L;

    ListNode<Integer> slow = L, fast = L, preSlow=null;
    while(fast != null && fast.next !=null){
      preSlow = slow;
      slow = slow.next;
      fast = fast.next.next;
    }

    preSlow.next = null;

    return merge(stableSortList(L), stableSortList(slow));
  }

  private static ListNode<Integer> merge(ListNode<Integer> a, ListNode<Integer> b) {

    if (a == null) return b;
    if (b == null) return a;
    ListNode<Integer> curr = new ListNode<Integer>(0, null);
    ListNode<Integer> ans = curr;


    while (a != null && b != null) {
      if (a.data < b.data) {
        curr.next = a;
        a = a.next;
      } else {
        curr.next = b;
        b = b.next;
      }

      curr = curr.next;

    }
    if(a==null) curr.next = b;
    else curr.next = a;
    return ans.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
