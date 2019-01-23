package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
public class LargestRectangleUnderSkyline {
  @EpiTest(testDataFile = "largest_rectangle_under_skyline.tsv")

  public static int calculateLargestRectangle(List<Integer> heights) {
    // TODO - you fill in here.
//    return nSquaredSolution(heights);

//    return betterSol(heights);

    return recursiveSol(heights, 0, heights.size()-1);

  }

  private static int recursiveSol(List<Integer> ip, int s, int e) {
    if(s>e)
      return 0;

    int min = ip.get(s);
    int minIndex = s;
    int c = s;

    while(c<=e){
      if(ip.get(c) < min) {
        min = ip.get(c);
        minIndex = c;
      }
      c++;
    }

    int leftArea = recursiveSol(ip,s,minIndex-1);
    int rightArea = recursiveSol(ip, minIndex+1, e);

    return Math.max((e-s+1)*min, Math.max(leftArea, rightArea));
  }

  private static int betterSol(List<Integer> heights) {
    Deque<Integer> stack = new ArrayDeque<>();
    int max = 0;
//    stack.push(-1);
    int i = 0;
    while (i < heights.size()) {
      if (stack.isEmpty()) stack.push(i);
      else {
        if (heights.get(stack.peek()) > heights.get(i)) {
          stack.pop();
          max = Math.max(max, Math.min(heights.get(i), heights.get(stack.peek())) * (i - stack.peek() + 1));
          stack.push(i);
        } else {
          max = Math.max(max, Math.min(heights.get(i), heights.get(stack.peek())) * (i - stack.peek() + 1));
        }
      }
      i++;
    }

    while (!stack.isEmpty()) {
      Integer j = stack.pop();

      if (stack.isEmpty()) break;

      i = stack.peek();
      max = Math.max(max, Math.min(heights.get(j), heights.get(i)) * (j - i + 1));

    }

    return max;
  }

  private static int nSquaredSolution(List<Integer> heights) {
    //  i , j & let us keep track of min for j so far and find area
    int max = 0;
//    System.out.println(heights);
    for ( int i = 0 ; i < heights.size(); i++){
      int min = Integer.MAX_VALUE;
      for (int j=i; j< heights.size(); j++){
          min = Math.min(min, heights.get(j));
          max = Math.max(max, min*(j-i+1));
      }
    }

    return max;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LargestRectangleUnderSkyline.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
