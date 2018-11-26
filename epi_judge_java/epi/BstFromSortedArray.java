package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TestUtils;
import epi.test_framework.TimedExecutor;
import java.util.List;
public class BstFromSortedArray {

  public static BstNode<Integer>
  buildMinHeightBSTFromSortedArray(List<Integer> A) {
    // TODO - you fill in here.
    return sol(A,0,A.size()-1);
  }

  private static BstNode<Integer> sol(List<Integer> ip, int s, int e) {
    if(s>e) return null;
    int mid = s+(e-s)/2;
    return new BstNode<>(ip.get(mid), sol(ip,s,mid-1), sol(ip, mid+1, e));
  }

  @EpiTest(testDataFile = "bst_from_sorted_array.tsv")
  public static int
  buildMinHeightBSTFromSortedArrayWrapper(TimedExecutor executor,
                                          List<Integer> A) throws Exception {
    BstNode<Integer> result =
        executor.run(() -> buildMinHeightBSTFromSortedArray(A));

    List<Integer> inorder = BinaryTreeUtils.generateInorder(result);

    TestUtils.assertAllValuesPresent(A, inorder);
    BinaryTreeUtils.assertTreeIsBst(result);
    return BinaryTreeUtils.binaryTreeHeight(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BstFromSortedArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
