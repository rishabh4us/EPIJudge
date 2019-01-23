package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class SearchRowColSortedMatrix {
  @EpiTest(testDataFile = "search_row_col_sorted_matrix.tsv")

  public static boolean matrixSearch(List<List<Integer>> A, int x) {
    // TODO - you fill in here.
    if(A.isEmpty()) return false;

    int r = 0, c = A.get(0).size()-1;

    while( r<A.size()&&c>=0){

      if(A.get(r).get(c) == x) return true;
      else if(A.get(r).get(c)<x) r++;
      else c--;
    }

    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchRowColSortedMatrix.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
