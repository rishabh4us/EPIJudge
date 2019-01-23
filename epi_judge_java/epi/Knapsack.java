package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import java.util.List;
public class Knapsack {
  @EpiUserType(ctorParams = {Integer.class, Integer.class})

  public static class Item {
    public Integer weight;
    public Integer value;

    public Item(Integer weight, Integer value) {
      this.weight = weight;
      this.value = value;
    }
  }

  @EpiTest(testDataFile = "knapsack.tsv")

  public static int optimumSubjectToCapacity(List<Item> items, int capacity) {
    // TODO - you fill in here.

//    return fullSpaceALgo(items, capacity);
    int dp[]= new int [capacity+1];
    for(int i=1; i<= items.size(); i ++){
      for(int j = 1; j<= capacity; j++){


        dp[j] = dp[j];
        int remaining = j - items.get(i - 1).weight;
        if(remaining >= 0 ){
          dp[j] = Math.max(dp[j], items.get(i-1).value + dp[remaining] );
        }
      }
    }
    return dp[capacity];

  }

  private static int fullSpaceALgo(List<Item> items, int capacity) {
    int dp[][] = new int [items.size()+1][capacity+1];
    for(int i=1; i<= items.size(); i ++){
      for(int j = 1; j<= capacity; j++){


        dp[i][j] = dp[i-1][j];
        int remaining = j - items.get(i - 1).weight;
        if(remaining >= 0 ){
          dp[i][j] = Math.max(dp[i-1][j], items.get(i-1).value + dp[i-1][remaining] );
        }
      }
    }
    return dp[items.size()][capacity];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Knapsack.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
