package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeSymmetric {
  @EpiTest(testDataFile = "is_tree_symmetric.tsv")

  public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    if (tree==null) return true;
    return sol(tree.left, tree.right);
  }

  private static boolean sol(BinaryTreeNode<Integer> left, BinaryTreeNode<Integer> right) {
    if(left==null&&right==null) return true;
    if (left == null || right == null) return false;
    if (!left.data.equals(right.data)) return false;


    return sol(left.left, right.right) && sol(left.right, right.left);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeSymmetric.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
