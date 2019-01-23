package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
public class NQueens {
  @EpiTest(testDataFile = "n_queens.tsv")

  public static List<List<Integer>> nQueens(int n) {
    // TODO - you fill in here.

    int[][] board = new int[n][n];
    int row = 0;
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> tmp = new ArrayList<>();
    sol(n, board, ans, row, tmp);
    return ans;
  }

  private static void sol(int n, int[][] board, List<List<Integer>> ans, int row, List<Integer> tmp) {
    if (row >= n) {
      ans.add(new ArrayList<>(tmp));
//      ans.add((tmp));
      return;
    }

    for (int col = 0; col < n; col++) {
      if (isAvailable(board, row, col)) {
        place(board, row, col);
        tmp.add(col);

        sol(n, board, ans, row + 1, tmp);

        remove(board, row, col);
        tmp.remove(tmp.size() - 1);
      }
    }
  }

  private static void remove(int[][] board, int row, int col) {
    for (int i = 0; i < board.length; i++) board[row][i]--;
    for (int i = 0; i < board.length; i++) board[i][col]--;
    board[row][col]++;
    int n = board.length, r = row - 1, c = col - 1;
    while (r >= 0 && c >= 0) {
      board[r--][c--]--;
    }
    r = row - 1;
    c = col + 1;
    while (r >= 0 && c < n) {
      board[r--][c++]--;
    }

    r = row + 1;
    c = col + 1;
    while (r < n && c < n) {
      board[r++][c++]--;
    }
    r = row + 1;
    c = col - 1;
    while (r < n && c >= 0) {
      board[r++][c--]--;
    }

  }

  private static void place(int[][] board, int row, int col) {
    for (int i = 0; i < board.length; i++) board[row][i] += 1;
    for (int i = 0; i < board.length; i++) board[i][col] += 1;
    board[row][col]--;
    int n = board.length, r = row - 1, c = col - 1;
    while (r >= 0 && c >= 0) {
      board[r--][c--] += 1;
    }
    r = row - 1;
    c = col + 1;
    while (r >= 0 && c < n) {
      board[r--][c++] += 1;
    }

    r = row + 1;
    c = col + 1;
    while (r < n && c < n) {
      board[r++][c++] += 1;
    }
    r = row + 1;
    c = col - 1;
    while (r < n && c >= 0) {
      board[r++][c--] += 1;
    }
  }

  private static boolean isAvailable(int[][] board, int row, int col) {
    if (board[row][col] == 0) return true;
    else return false;
  }


  @EpiTestComparator
  public static BiPredicate<List<List<Integer>>, List<List<Integer>>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    expected.sort(new LexicographicalListComparator<>());
    result.sort(new LexicographicalListComparator<>());
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NQueens.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
