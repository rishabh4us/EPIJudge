package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IsStringInMatrix {
  @EpiTest(testDataFile = "is_string_in_matrix.tsv")
  public static boolean isPatternContainedInGrid(List<List<Integer>> grid,
                                                 List<Integer> pattern) {
    // TODO - you fill in here.
//    return solCache(grid,pattern, 0,0,0, );
    Map<String, Boolean> cache = new HashMap<>();
    for (int I = 0; I < grid.size(); I++) {
      for (int J = 0; J < grid.get(I).size(); J++) {
        if (sol2(grid, pattern, I, J, 0, cache))
          return true;
      }
    }
    return false;
  }

  private static boolean solCache(List<List<Integer>> grid, List<Integer> pattern, int i, int j, int s, Map<String, Boolean> cache) {

    String key = "_" + i + "_" + j + "_" + s;
    if (cache.containsKey(key)) return cache.get(key);
    boolean ans = sol2(grid, pattern, i, j, s, cache);
    cache.put(key, ans);

    return ans;
  }

  private static boolean sol2(List<List<Integer>> grid, List<Integer> pattern, int i, int j, int s, Map<String, Boolean> cache) {
    if (s == pattern.size()) return true;


    if (i < 0 || i >= grid.size() || j < 0 || j >= grid.get(i).size() ) return false;

    if (grid.get(i).get(j) != pattern.get(s)) return false;

    else {
      if (solCache(grid, pattern, i, j - 1, s + 1, cache) ||
              solCache(grid, pattern, i, j + 1, s + 1, cache) ||
              solCache(grid, pattern, i + 1, j, s + 1, cache) ||
              solCache(grid, pattern, i - 1, j, s + 1, cache)) return true;
    }
    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringInMatrix.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
