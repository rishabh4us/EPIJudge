package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IntSquareRoot {
  @EpiTest(testDataFile = "int_square_root.tsv")

  public static int squareRoot(int k) {
    // TODO - you fill in here.

    int left = 0, right = k;
    while (left<=right){
      int mid = left + (right-left)/2;
      long sq = (long)mid * mid;

//      System.out.println(counter++ + " , "+left+" , "+mid+" , "+ right +" , "+sq);

      if (sq==k) return mid;
      else if(sq < k) left = mid + 1;
      else right = mid - 1;
    }

    return left-1;

  }
//TEST CASES
//  integer = max int
//  1 element
//  0,1,2r,3l,4 - 7

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntSquareRoot.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
