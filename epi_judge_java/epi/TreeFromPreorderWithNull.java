package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class TreeFromPreorderWithNull {

  static int currIdx;

  public static BinaryTreeNode<Integer> reconstructPreorder(List<Integer> preorder) {
    // TODO - you fill in here.
//preorder = Arrays.asList(1,2,null, null, 3,null,null);
    currIdx = 0;

    return sol(preorder);
  }

  // 1 2 n n 3 n n

  public static BinaryTreeNode<Integer> sol(List<Integer> preorder) {

    Integer value = preorder.get(currIdx);
    currIdx++;

    if (value == null) return null;
    BinaryTreeNode<Integer> head = new BinaryTreeNode<>(value);
    head.left = sol(preorder);
    head.right = sol(preorder);

    return head;
  }


//  public static BinaryTreeNode<Integer> sol(List<Integer> preorder) {
//
//    if (preorder.get(currIdx) == null) return null;
//
//    BinaryTreeNode<Integer> head = new BinaryTreeNode<>(preorder.get(currIdx));
//
//    currIdx++;
//    head.left = sol(preorder);
//
//    currIdx++;
//    head.right = sol(preorder);
//
//    return head;
//  }


  @EpiTest(testDataFile = "tree_from_preorder_with_null.tsv")
  public static BinaryTreeNode<Integer>
  reconstructPreorderWrapper(TimedExecutor executor, List<String> strings)
      throws Exception {
    List<Integer> ints = new ArrayList<>();
    for (String s : strings) {
      if (s.equals("null")) {
        ints.add(null);
      } else {
        ints.add(Integer.parseInt(s));
      }
    }

    return executor.run(() -> reconstructPreorder(ints));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeFromPreorderWithNull.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
