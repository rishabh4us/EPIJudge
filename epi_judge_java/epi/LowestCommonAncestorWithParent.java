package epi;

import epi.test_framework.*;
public class LowestCommonAncestorWithParent {

  public static BinaryTree<Integer> LCA(BinaryTree<Integer> node0,
                                        BinaryTree<Integer> node1) {
    // TODO - you fill in here.
    if (node0 == null || node1 == null) return null;
    int d0 = getDepth(node0);
    int d1 = getDepth(node1);
    BinaryTree<Integer> big = node0;
    BinaryTree<Integer> small = node1;
    if (d1 > d0) {
      big = node1;
      small = node0;
    }
    int diff = Math.abs(d0 - d1);
    while (diff > 0) {
      diff--;
      big = big.parent;
    }

    while (big != small) {
      big = big.parent;
      small = small.parent;
    }

    return small;
  }

  private static int getDepth(BinaryTree<Integer> node1) {
    if (node1 == null) return 0;
    BinaryTree<Integer> tmp = node1;
    int depth = 0;
    while (tmp.parent != null) {
      depth++;
      tmp = tmp.parent;
    }
    return depth;
  }

  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor, BinaryTree<Integer> tree,
                               Integer key0, Integer key1) throws Exception {
    BinaryTree<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTree<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTree<Integer> result = executor.run(() -> LCA(node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestorWithParent.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
