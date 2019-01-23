package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;
public class SudokuSolve {
  public static boolean solveSudoku(List<List<Integer>> partialAssignment) {
    sol(partialAssignment, 0,0);
    return true;
  }
//
  private static boolean sol(List<List<Integer>> partialAssignment, int row, int col) {
    System.out.println(row+" "+col);

    if (row == partialAssignment.size()) return true;

    if(col == partialAssignment.size()) return sol(partialAssignment, row+1, 0);

    if(partialAssignment.get(row).get(col) !=0 ) return sol(partialAssignment, row, col+1);

    for (int i = 1; i <= 9; i++) {
      if (canPlace(partialAssignment, row, col, i)) {
        partialAssignment.get(row).set(col, i);
        if( sol(partialAssignment, row, col + 1)) return true;
        else partialAssignment.get(row).set(col, 0);
      }
    }

return false;
  }

  private static boolean canPlace(List<List<Integer>> partialAssignment, int row, int col, int val) {
    for(int i=0; i< partialAssignment.size(); i++){
      if(partialAssignment.get(i).get(col) == val) return false;
    }
    for(int i=0; i< partialAssignment.size(); i++){
      if(partialAssignment.get(row).get(i) == val) return false;
    }
     int region = (int)Math.sqrt(partialAssignment.size());
    int regStartR = row / region, regStartC = col/region;
    for(int i=0; i< region; i++){
      for(int j=0; j< region; j++){
        if(partialAssignment.get(regStartR*region +i).get(regStartC*region +j ) == val) return false;
      }
    }
    return true;
  }

  @EpiTest(testDataFile = "sudoku_solve.tsv")
  public static void solveSudokuWrapper(TimedExecutor executor,
                                        List<List<Integer>> partialAssignment)
      throws Exception {
    List<List<Integer>> solved = new ArrayList<>();
    for (List<Integer> row : partialAssignment) {
      solved.add(new ArrayList<>(row));
    }

    executor.run(() -> solveSudoku(solved));

    if (partialAssignment.size() != solved.size()) {
      throw new TestFailure("Initial cell assignment has been changed");
    }

    for (int i = 0; i < partialAssignment.size(); i++) {
      List<Integer> br = partialAssignment.get(i);
      List<Integer> sr = solved.get(i);
      if (br.size() != sr.size()) {
        throw new TestFailure("Initial cell assignment has been changed");
      }
      for (int j = 0; j < br.size(); j++)
        if (br.get(j) != 0 && !Objects.equals(br.get(j), sr.get(j)))
          throw new TestFailure("Initial cell assignment has been changed");
    }

    int blockSize = (int)Math.sqrt(solved.size());
    for (int i = 0; i < solved.size(); i++) {
      assertUniqueSeq(solved.get(i));
      assertUniqueSeq(gatherColumn(solved, i));
      assertUniqueSeq(gatherSquareBlock(solved, blockSize, i));
    }
  }

  private static void assertUniqueSeq(List<Integer> seq) throws TestFailure {
    Set<Integer> seen = new HashSet<>();
    for (Integer x : seq) {
      if (x == 0) {
        throw new TestFailure("Cell left uninitialized");
      }
      if (x < 0 || x > seq.size()) {
        throw new TestFailure("Cell value out of range");
      }
      if (seen.contains(x)) {
        throw new TestFailure("Duplicate value in section");
      }
      seen.add(x);
    }
  }

  private static List<Integer> gatherColumn(List<List<Integer>> data, int i) {
    List<Integer> result = new ArrayList<>();
    for (List<Integer> row : data) {
      result.add(row.get(i));
    }
    return result;
  }

  private static List<Integer> gatherSquareBlock(List<List<Integer>> data,
                                                 int blockSize, int n) {
    List<Integer> result = new ArrayList<>();
    int blockX = n % blockSize;
    int blockY = n / blockSize;
    for (int i = blockX * blockSize; i < (blockX + 1) * blockSize; i++) {
      for (int j = blockY * blockSize; j < (blockY + 1) * blockSize; j++) {
        result.add(data.get(i).get(j));
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SudokuSolve.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
