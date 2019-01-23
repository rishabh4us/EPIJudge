package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
public class TreeInorder {
  @EpiTest(testDataFile = "tree_inorder.tsv")

  public static List<Integer> inorderTraversal(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    List<Integer> ans = new ArrayList<>();
    Deque<BinaryTreeNode<Integer>> stack = new ArrayDeque<>();
    BinaryTreeNode<Integer> h = tree;
    while (true) {
      if (h != null) {
        stack.push(h);
        h = h.left;
      } else {
        if (stack.isEmpty()) break;

        h = stack.pop();
        ans.add(h.data);
        h = h.right;
      }
    }

    return ans;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
