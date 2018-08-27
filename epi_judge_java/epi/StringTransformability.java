package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
public class StringTransformability {


  static class StringDist{
    String str;
    int dist;
    StringDist(String str, int dist){
      this.str = str;
      this.dist = dist;
    }
  }
  // Uses BFS to find the least steps of transformation.
  @EpiTest(testDataFile = "string_transformability.tsv")
  public static int transformString(Set<String> D, String s, String t) {
    // TODO - you fill in here.

    Queue<StringDist> q = new ArrayDeque<StringDist>();
    q.add(new StringDist(s, 0));

    Set<String> visited = new HashSet<>();


    while (!q.isEmpty()) {
//      for (StringDist i : q) {
//        System.out.print(i.str + ",");
//      }
//      System.out.println();


      StringDist curr = q.remove();
      String tmp = curr.str;
      visited.add(tmp);
      if (tmp.equals(t)) return curr.dist;


      for (int i = 0; i < tmp.length(); i++) {
        String part1 = i == 0 ? "" : tmp.substring(0, i);
        String part2 = i + 1 < tmp.length() ? tmp.substring(i + 1, tmp.length()) : "";
        for (int j = 0; j < 26; j++) {
          String newStr = part1 + (char) ('a' + j) + part2;
//          System.out.println("available: " + newStr);

          if (D.contains(newStr) && !visited.contains(newStr)) {
            q.add(new StringDist(newStr, curr.dist + 1));
          }
        }

      }

    }


    return -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringTransformability.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
