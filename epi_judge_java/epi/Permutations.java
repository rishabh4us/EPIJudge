package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.LexicographicalListComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
public class Permutations {
  @EpiTest(testDataFile = "permutations.tsv")

  public static List<List<Integer>> permutations(List<Integer> A) {
    // TODO - you fill in here.
    List<List<Integer>> ans= new ArrayList<>();
//    List<Integer> curr = new ArrayList<>();

    sol(A,0, ans);
//    System.out.println(ans);
    return ans;
  }

  private static void sol(List<Integer> ip, int s, List<List<Integer>> ans) {
    if(s==ip.size()){
      ans.add(new ArrayList<>(ip));
      return;
    }

    // don't choose n and permute remaining
    //choose n & permute remaining n-1
    for(int i=s; i<ip.size(); i++ ){
      Collections.swap(ip, s, i);
//      curr.add(ip.get(s));
      sol(ip,s+1, ans);
//      curr.remove(curr.size()-1);
      Collections.swap(ip, s, i);
    }
  }



  @EpiTestComparator
  public static BiPredicate<List<List<Integer>>, List<List<Integer>>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    for (List<Integer> l : expected) {
      Collections.sort(l);
    }
    expected.sort(new LexicographicalListComparator<>());
    for (List<Integer> l : result) {
      Collections.sort(l);
    }
    result.sort(new LexicographicalListComparator<>());
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Permutations.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
