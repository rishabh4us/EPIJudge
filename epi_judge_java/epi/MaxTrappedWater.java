package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class MaxTrappedWater {
  @EpiTest(testDataFile = "max_trapped_water.tsv")

  public static int getMaxTrappedWater(List<Integer> heights) {
    // TODO - you fill in here.
    int maxwater = 0, i=0, j= heights.size()-1;

    while ( i<j ){
      int width = j-i;

      int h = Math.min(heights.get(i), heights.get(j));
      maxwater = maxwater < h*width ? h*width : maxwater;
      if(heights.get(i) > heights.get(j)) j--;
      else i++;


    }
    return maxwater;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MaxTrappedWater.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
