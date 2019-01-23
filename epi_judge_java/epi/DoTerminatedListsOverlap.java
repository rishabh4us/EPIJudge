package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class DoTerminatedListsOverlap {

  public static ListNode<Integer>
  overlappingNoCycleLists(ListNode<Integer> l0, ListNode<Integer> l1) {
    // TODO - you fill in here.

    ListNode<Integer> l = l0, r = l1;

    int c1=0, c2=0;
    while(l!=null){
      c1++;
      l=l.next;
    }
    while(r!=null){
      c2++;
      r=r.next;
    }

    if(c1 * c2 == 0) return null;

    int diff = c1-c2;
    l = l0; r = l1;
    while(diff != 0){
      if(diff >0 ){
        diff--;
        l = l.next;
      }
      else {
        diff++;
        r = r.next;
      }
    }

    while(r !=null){
      if(l==r) return r;
      l=l.next;
      r=r.next;
    }

    return null;
  }
  @EpiTest(testDataFile = "do_terminated_lists_overlap.tsv")
  public static void
  overlappingNoCycleListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
                                 ListNode<Integer> l1, ListNode<Integer> common)
      throws Exception {
    if (common != null) {
      if (l0 != null) {
        ListNode<Integer> i = l0;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l0 = common;
      }

      if (l1 != null) {
        ListNode<Integer> i = l1;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l1 = common;
      }
    }

    final ListNode<Integer> finalL0 = l0;
    final ListNode<Integer> finalL1 = l1;
    ListNode<Integer> result =
        executor.run(() -> overlappingNoCycleLists(finalL0, finalL1));

    if (result != common) {
      throw new TestFailure("Invalid result");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DoTerminatedListsOverlap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
