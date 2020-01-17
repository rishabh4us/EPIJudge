package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class IsValidSudoku {
  @EpiTest(testDataFile = "is_valid_sudoku.tsv")

  // Check if a partially filled matrix has any conflicts.
  public static boolean isValidSudoku(List<List<Integer>> partialAssignment) {

    // rows
    for (int i = 0 ; i < partialAssignment.size() ; i++) {
      if (hasDuplicate(partialAssignment, i, i+1, 0, partialAssignment.size())) {
        return false;
      }
    }

    // columns
    for (int  j = 0 ; j < partialAssignment.size() ; j++) {
      if (hasDuplicate(partialAssignment, 0, partialAssignment.size(), j ,j+1)) {
        return false;
      }
    }

    // regions/grids
    int regionSize = (int) Math.sqrt(partialAssignment.size());
    for (int i = 0; i < regionSize ;i++) {
      for (int j = 0; j < regionSize; j++) {
        if (hasDuplicate(partialAssignment, regionSize * i, regionSize * (i + 1), regionSize * j, regionSize * (j + 1))) {
          return false;
        }
      }
    }

    return true;
  }

  private static boolean hasDuplicate(List<List<Integer>> partialAssignment, int startRow, int endRow, int startCol, int endCol) {

    List<Boolean> isPresent = new ArrayList<>(Collections.nCopies(partialAssignment.size() + 1, false));
    for (int i = startRow; i < endRow; i++ ) {
      for (int j = startCol; j < endCol; j++) {
        if (partialAssignment.get(i).get(j) != 0 && isPresent.get(partialAssignment.get(i).get(j))) {
          return true;
        }
        isPresent.set(partialAssignment.get(i).get(j), true);
      }
    }
    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsValidSudoku.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
