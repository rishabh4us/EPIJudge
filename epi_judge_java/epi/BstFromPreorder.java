package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class BstFromPreorder {
  @EpiTest(testDataFile = "bst_from_preorder.tsv")

  public static BstNode<Integer>
  rebuildBSTFromPreorder(List<Integer> preorderSequence) {
    // TODO - you fill in here.
//    System.out.println(preorderSequence);

    return sol(preorderSequence, 0, preorderSequence.size() - 1);
  }

  private static BstNode<Integer> sol(List<Integer> ip, int s, int e) {
    if( s>e ) return null;
    if(s == e) return new BstNode<>(ip.get(s),null,null);
    int p = s;
    while ( p<ip.size() && ip.get(p) <= ip.get(s)) {
      p++;
    }
    return new BstNode<Integer>(ip.get(s), sol(ip, s+1, p - 1), sol(ip, p, e));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BstFromPreorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
