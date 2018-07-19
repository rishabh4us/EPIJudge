package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Map;

public class IsTreeBalanced {

  @EpiTest(testDataFile = "is_tree_balanced.tsv")


  // this is bottom up approach, we are returning from root
  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.

    if (tree == null)
      return true;
//    Map<BinaryTreeNode<Integer>, Integer> cache = new HashMap<>();
    final int left = height(tree.left);
    final int right = height(tree.right);
//    final int left = height1(cache, tree.left);
//    final int right = height1(cache, tree.right);
    if (left == -1 || right == -1) return false;
    int diff = Math.abs(left - right);
    return (diff <= 1 && diff >= 0 ? true : false);// && isBalanced(tree.left) && isBalanced(tree.right);
    // this calling isbalanced() again and again is bad because it ruins the point

  }

  static int height(BinaryTreeNode<Integer> node) {
    int ans = -1;
    if (node == null) return 0;
    else {
      final int left = height(node.left);
      final int right = height(node.right);
      ans = (Math.abs(left - right) <= 1 && !(left == -1 || right == -1)) ? 1 + Math.max(left, right) : -1;
    }
//    System.out.print(cache.get(node) + " ");
    return ans;
  }

  static int height1(Map<BinaryTreeNode<Integer>, Integer> cache, BinaryTreeNode<Integer> node) {
    if (cache.get(node) != null) return cache.get(node);

    if (node == null) cache.put(node, 0);
    else {
      final int left = height1(cache, node.left);
      final int right = height1(cache, node.right);
      final int ans = (Math.abs(left - right) <= 1 && !(left == -1 || right == -1)) ? 1 + Math.max(left, right) : -1;
      cache.put(node, ans);
    }
//    System.out.print(cache.get(node) + " ");
    return cache.get(node);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
