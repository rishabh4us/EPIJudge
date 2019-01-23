package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseSublist {
  @EpiTest(testDataFile = "reverse_sublist.tsv")

  public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start,
                                                 int finish) {
    // TODO - you fill in here.

    ListNode mainHead = new ListNode(0,L);
    ListNode head = mainHead;
    int counter = 1;
    while(counter++ < start){
      head = head.next;
    }

    ListNode runner = head.next;

    while(start++ < finish){
      swapNextToFront(head, runner);
    }

    return mainHead.next;
  }

  private static void swapNextToFront(ListNode head, ListNode runner) {
    ListNode tmp = runner.next;
    runner.next = tmp.next;
    tmp.next = head.next;
    head.next = tmp;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseSublist.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
