package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeBalanced {

  private static class BalanceStatusWithHeight {
    public boolean isBalanced;
    public int height;

    public BalanceStatusWithHeight(boolean isBalanced, int height) {
      this.isBalanced = isBalanced;
      this.height = height;
    }
  }

  @EpiTest(testDataFile = "is_tree_balanced.tsv")
  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    return checkBalanced(tree).isBalanced;
  }

  private static BalanceStatusWithHeight checkBalanced(BinaryTreeNode<Integer> tree) {

    if (tree == null) {
      return new BalanceStatusWithHeight(true, -1);
    }

    BalanceStatusWithHeight leftResult = checkBalanced(tree.left);
    if (!leftResult.isBalanced) {
      return leftResult;
    }

    BalanceStatusWithHeight rightResult = checkBalanced(tree.right);
    if (!rightResult.isBalanced) {
      return rightResult;
    }

    boolean isBalanced = Math.abs(leftResult.height - rightResult.height) <= 1;
    int height = Math.max(leftResult.height, rightResult.height) + 1;
    return new BalanceStatusWithHeight(isBalanced, height);

  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
