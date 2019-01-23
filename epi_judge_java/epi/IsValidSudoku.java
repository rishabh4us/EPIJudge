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
    for (int i = 0; i < partialAssignment.size(); i++)
      if (hasDuplicates(partialAssignment, i, i + 1, 0, partialAssignment.size()))
        return false;

    for (int j = 0; j < partialAssignment.size(); j++)
      if (hasDuplicates(partialAssignment, 0,  partialAssignment.size(), j,j+1))
        return false;

    int regionSize = (int) Math.sqrt(partialAssignment.size());

    for (int i = 0; i < regionSize; i++)
      for (int j = 0; j < regionSize; j++){
          if(hasDuplicates(partialAssignment,
                  regionSize * i, regionSize* (i+1),
                  regionSize*j, (j+1)*regionSize))
            return false;
      }
      return true;

  }

  private static boolean hasDuplicates(List<List<Integer>> partialAssignment, int rstart, int rend, int cstart, int cend) {
    boolean[] isPresent = new boolean[partialAssignment.size()+1];
    for (int i = rstart; i < rend; i++)
      for (int j = cstart; j < cend; j++){
        if(partialAssignment.get(i).get(j) !=0 && isPresent[partialAssignment.get(i).get(j)] )
          return true;
        else
          isPresent[partialAssignment.get(i).get(j)] = true;
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
