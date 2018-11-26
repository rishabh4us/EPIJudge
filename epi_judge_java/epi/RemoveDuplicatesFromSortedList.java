package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class RemoveDuplicatesFromSortedList {
  @EpiTest(testDataFile = "remove_duplicates_from_sorted_list.tsv")

  public static ListNode<Integer> removeDuplicates(ListNode<Integer> L) {
    // TODO - you fill in here.
if (L== null ) return null;
    List<Integer> l = L.toArray();
    if(l.size()<2) return L;

    int i = 0 ;
    for (int j = 1 ; j< l.size();j++){
      if(l.get(j) == l.get(i)) continue;

      Collections.swap(l,++i,j);
    }

    ListNode<Integer> ans  = new ListNode<>(0,null);
    ListNode<Integer> head = ans;
    int c = 0;
    while(c<=i){
      head.next = new ListNode<Integer>(l.get(c), null);
      head = head.next;
      c++;
    }
    return ans.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RemoveDuplicatesFromSortedList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
