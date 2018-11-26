package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class LowestCommonAncestor {

  static class Status {
    int num;
    BinaryTreeNode<Integer> result;

    Status(int n, BinaryTreeNode<Integer> r) {
      num = n;
      result = r;
    }

  }

  public static BinaryTreeNode<Integer> LCA(BinaryTreeNode<Integer> tree,
                                            BinaryTreeNode<Integer> node0,
                                            BinaryTreeNode<Integer> node1) {
    // TODO - you fill in here.
    return sol(tree, node0, node1).result;
  }

  private static Status sol(BinaryTreeNode<Integer> h, BinaryTreeNode<Integer> a, BinaryTreeNode<Integer> b) {

    if (h == null) return new Status(0, null);

    Status lstatus = sol(h.left, a, b);
    if (lstatus.num == 2) return lstatus;
    Status rstatus = sol(h.right, a, b);
    if (rstatus.num == 2) return rstatus;


    int currCount = lstatus.num + rstatus.num + (h.equals(a) ? 1 : 0) + (h.equals(b) ? 1 : 0);

    if (currCount == 2) return new Status(2, h);

    return new Status(currCount, null);
  }

  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor,
                               BinaryTreeNode<Integer> tree, Integer key0,
                               Integer key1) throws Exception {
    BinaryTreeNode<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTreeNode<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTreeNode<Integer> result =
        executor.run(() -> LCA(tree, node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestor.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
