package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsListPalindromic {
  @EpiTest(testDataFile = "is_list_palindromic.tsv")

  public static boolean isLinkedListAPalindrome(ListNode<Integer> L) {
    // TODO - you fill in here.
    if(L == null || L.next == null) return true;
    ListNode<Integer> mid = getMid(L);

    ListNode<Integer> rhalf = reverse(mid);

    ListNode<Integer> n = L;
    ListNode<Integer> m = rhalf;
    while(m != null ){
      if(!n.data.equals(m.data)) return false;
      n=n.next;
      m=m.next;

    }

    return true;
  }

  //  a b c - c b a
  //  a b c d - d c b a
  public static ListNode<Integer> reverse(ListNode<Integer> h){
    ListNode<Integer> prev = null;
    ListNode<Integer> curr = h;

    while(curr != null){
      ListNode<Integer> temp = curr.next;
      curr.next = prev;

      prev = curr;
      curr = temp;

    }
    return prev;
  }

//  a b c d - return c
//  a b c d e  return d
  public static ListNode<Integer> getMid(ListNode<Integer> h){
    ListNode<Integer> runner=h;
    while (runner.next!=null && runner.next.next!=null){
      runner = runner.next.next;
      h = h.next;
    }
    return h.next;
  }



  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsListPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
