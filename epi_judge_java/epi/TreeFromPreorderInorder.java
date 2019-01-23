package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeFromPreorderInorder {
  @EpiTest(testDataFile = "tree_from_preorder_inorder.tsv")

  public static BinaryTreeNode<Integer>
  binaryTreeFromPreorderInorder(List<Integer> preorder, List<Integer> inorder) {
    Map<Integer, Integer> inorderIndexMap = new HashMap<>();
    for(int i=0;i<inorder.size();i++){
      inorderIndexMap.put(inorder.get(i),i);
    }
    return  sol(preorder,0,preorder.size()-1, inorder,0,inorder.size()-1,inorderIndexMap);
  }

  private static BinaryTreeNode<Integer> sol(List<Integer> preorder, int ps, int pe, List<Integer> inorder, int is, int ie, Map<Integer, Integer> inorderIndexMap) {

//    hbacde
//    bac H de

    if(ps>pe || is>pe) return null;
//    int indexOfRoot = inorder.indexOf(preorder.get(ps));
    int indexOfRoot = inorderIndexMap.get(preorder.get(ps));
    int numLeftChild = indexOfRoot - is;

    BinaryTreeNode<Integer>  left = sol(preorder,
            ps +1, ps + numLeftChild,
            inorder, is, indexOfRoot-1, inorderIndexMap);
    BinaryTreeNode<Integer>  right = sol(preorder,
            ps + numLeftChild + 1, pe,
            inorder, indexOfRoot  +1, ie, inorderIndexMap);

    return new BinaryTreeNode<Integer>(preorder.get(ps),left,right);

  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeFromPreorderInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
