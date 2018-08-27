package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeABst {
  @EpiTest(testDataFile = "is_tree_a_bst.tsv")

  public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    return isBST(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }
 //runtime O(n) but space O(h) //

  private static boolean isBST(BinaryTreeNode<Integer> tree, int minValue, int maxValue) {
    if (tree == null) return true;
    if (minValue > tree.data || maxValue < tree.data) return false;
    return isBST(tree.left, minValue, tree.data) && isBST(tree.right, tree.data, maxValue);
  }

//  if using queue we can do a DFS and put a class(node, min, max ) in queue
//time o(n) space (O(n))


  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeABst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
