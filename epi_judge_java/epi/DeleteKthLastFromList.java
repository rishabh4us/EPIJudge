package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import jdk.nashorn.internal.ir.RuntimeNode;

public class DeleteKthLastFromList {
  @EpiTest(testDataFile = "delete_kth_last_from_list.tsv")

  // Assumes L has at least k nodes, deletes the k-th last node in L.
  public static ListNode<Integer> removeKthLast(ListNode<Integer> L, int k)  {
    // TODO - you fill in here.

    ListNode<Integer> dummyHead = new ListNode<Integer>(0,L);

    int counter = k+1 ;
    ListNode<Integer> curr = dummyHead, runner = dummyHead;
    while (counter-->0 && runner!=null){
      runner = runner.next;
    }
//    if (runner == null) throw new Exception("k is too big");
     while(runner!=null){

     runner = runner.next;
     curr = curr.next;
     }
    curr.next = curr.next.next;
    return dummyHead.next ;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DeleteKthLastFromList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
