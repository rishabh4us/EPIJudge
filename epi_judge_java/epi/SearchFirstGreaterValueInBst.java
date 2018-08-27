package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SearchFirstGreaterValueInBst {

  public static BstNode<Integer> findFirstGreaterThanK(BstNode<Integer> tree,
                                                       Integer k) {
    // TODO - you fill in here.
    BstNode<Integer> ans = null;
    while(tree!=null) {
      if (tree.data<=k) tree = tree.right;
      else if (tree.data>k){
        ans = tree;
        tree = tree.left;
      }
    }
    return ans;












//    BstNode<Integer> head = tree;
//    BstNode<Integer> ans = null;
//
//    while(head != null ){
//      if (head.data > k ){
//        ans = head;
//        head = head.left;
//      } else {
//        head = head.right;
//      }
//    }

//    return ans;
  }
  @EpiTest(testDataFile = "search_first_greater_value_in_bst.tsv")
  public static int findFirstGreaterThanKWrapper(BstNode<Integer> tree,
                                                 Integer k) {
    BstNode<Integer> result = findFirstGreaterThanK(tree, k);
    return result != null ? result.data : -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstGreaterValueInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
