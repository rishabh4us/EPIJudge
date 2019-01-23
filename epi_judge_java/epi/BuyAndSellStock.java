package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class BuyAndSellStock {
  @EpiTest(testDataFile = "buy_and_sell_stock.tsv")
  public static double computeMaxProfit(List<Double> prices) {
    // TODO - you fill in here.
    if(prices.size()==0) return 0;
    double minSoFar = prices.get(0);
    double maxProfit = 0;
    for (Double price : prices){
      minSoFar = Math.min(price , minSoFar );
      maxProfit = Math.max(maxProfit, price- minSoFar);
    }
    return maxProfit;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStock.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
