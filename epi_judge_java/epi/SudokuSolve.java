package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
public class SudokuSolve {
  public static boolean solveSudoku(List<List<Integer>> partialAssignment) {
    // TODO - you fill in here.





    return sol(partialAssignment, 0,0);
  }

  private static boolean sol(List<List<Integer>> ip, int r, int c) {

    if(c==ip.size()) return sol(ip,r+1,0);
    if(r==ip.size()) return true;

    if(ip.get(r).get(c) != 0) return  sol(ip,r,c+1);

    for (int i=1; i<=ip.size(); i++){
      if(isValid(ip,r,c,i)){
        ip.get(r).set(c,i);
        if(sol(ip,r,c+1))
          return true;
      }
      ip.get(r).set(c,0); // reset
    }
    return false;
  }

  private static boolean isValid(List<List<Integer>> ip, int r, int c, int val) {
    for(int i=0;i<ip.size();i++){
      if(ip.get(i).get(c)==val) return false;
      if(ip.get(r).get(i)==val) return false;
    }
    int zoneSize = (int) Math.sqrt(ip.size());
    int rzone = r/zoneSize, czone = c/zoneSize;
    for( int i = 0 ; i<zoneSize; i++){
      for( int j = 0 ; j<zoneSize; j++){
        if(ip.get(i+rzone*zoneSize).get(j+czone*zoneSize) == val) return false;
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
