package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class ApplyPermutation {



    // NNED HELP __ :(


  public static void applyPermutation(List<Integer> perm, List<Integer> A) {
    // TODO - you fill in here.
    perm = Arrays.asList(3, 0, 1, 2);
    A =Arrays.asList( 1, 2, 3, 4);


    for (int index = 0; index < perm.size(); index++) {

      int i = index;
      int nextI = perm.get(i);
      if(nextI<0) continue;

      int nextV = A.get(nextI);
      int currV = A.get(i);

      while (nextI >= 0) {
        A.set(nextI, currV);

        currV = nextV;
        int tmp = i;
        i = nextI;
        nextI = perm.get(i);
        perm.set(tmp, perm.get(tmp) - perm.size());


      }
    }

    System.out.println(perm);
    System.out.println(A);

    return;
  }
  @EpiTest(testDataFile = "apply_permutation.tsv")
  public static List<Integer> applyPermutationWrapper(List<Integer> perm,
                                                      List<Integer> A) {
    applyPermutation(perm, A);
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ApplyPermutation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
