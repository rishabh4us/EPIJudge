package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class PowerXY {
  @EpiTest(testDataFile = "power_x_y.tsv")
  public static double power(double x, int y) {
    // TODO - you fill in here.
    // f = x ^ y ;
    // log f = y* log x
//    int sign = y % 2 == 0 ? 1 : (int) Math.signum(x);
//    return sign * Math.exp(y * Math.log(Math.abs(x)));

//    OR
    if (y == 0) return 1;
    if (y<0){
      x= 1/x;
      y  = -1*y;
    }
    double result = 1;
    while (y > 0) {
      if ((y & 1)==1) {
        result *= x;
      }
      x *= x;
      y >>>= 1;
    }
    return result;
//    return 0.0;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PowerXY.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
