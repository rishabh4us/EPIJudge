package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
public class MatrixEnclosedRegions {
 static class Cell {
   int r,c;

   Cell(int x, int y) {
     r = x;
     c = y;
   }
 }

  public static void fillSurroundedRegions(List<List<Character>> board) {
    // TODO - you fill in here.
    for (int i = 0; i < board.get(0).size(); i++) {
      helper(0, i, board);
      helper(board.size() - 1, i, board);
    }

    for (int i = 0; i < board.size(); i++) {

      helper(i, 0, board);
      helper(i, board.get(i).size() - 1, board);

    }

    for (int i = 0; i < board.size(); i++) {
      for (int j = 0; j < board.get(i).size(); j++) {
        board.get(i).set(j, board.get(i).get(j) == 'T' ? 'W' : 'B');
      }
    }

    return;
  }

  private static void helper(int r, int c, List<List<Character>> board) {
    Deque<Cell> queue = new ArrayDeque<>();
    queue.add(new Cell(r, c));
    while (!queue.isEmpty()) {
      Cell cl = queue.remove();
      if (cl.r >= 0 && cl.c >= 0 && cl.r < board.size() && cl.c < board.get(cl.r).size() && board.get(cl.r).get(cl.c) == 'W') {
//        board.get(cl.r).set(cl.c, board.get(r).get(cl.c) == 'W' ? 'T' : 'B');
        board.get(cl.r).set(cl.c, 'T');
        queue.add(new Cell(cl.r + 1, cl.c ));
        queue.add(new Cell(cl.r - 1, cl.c ));
        queue.add(new Cell(cl.r, cl.c + 1));
        queue.add(new Cell(cl.r , cl.c - 1));
      }
    }
  }

  @EpiTest(testDataFile = "matrix_enclosed_regions.tsv")
  public static List<List<Character>>
  fillSurroundedRegionsWrapper(List<List<Character>> board) {
    fillSurroundedRegions(board);
    return board;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MatrixEnclosedRegions.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
