package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class TreeLevelOrder {
  @EpiTest(testDataFile = "tree_level_order.tsv")

  public static List<List<Integer>> binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
    if (tree == null) return new ArrayList();

    List<List<Integer>> ans = new ArrayList();
    Deque<BinaryTreeNode<Integer>> queue = new ArrayDeque<>();
    queue.add(tree);

    while (!queue.isEmpty()){
      List<Integer> levelAns = new ArrayList();
      levelAns.addAll(queue.stream().map(x ->x.data).collect(Collectors.toList()));
      ans.add(levelAns);
      Deque<BinaryTreeNode<Integer>> children = new ArrayDeque<>();
      for(BinaryTreeNode<Integer> n : queue){
        if(n.left!= null) children.add(n.left);
        if(n.right!= null) children.add(n.right);
      }
      queue = children;
    }
    return ans;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeLevelOrder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
