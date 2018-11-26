package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class RealSquareRoot {
  @EpiTest(testDataFile = "real_square_root.tsv")

  public static double squareRoot(double x) {
      // TODO - you fill in here.
      System.out.println("input" + x);
      double s = 0;
      double e = x;
      double epsilon = .00000001;
      double mid = x;

      if(x<1) {
          s = x;
          e = 1;
      } else{
          s = 1;
          e = x;
      }

      if (Math.abs(x - 0) < epsilon) return 0;

//      while (Math.abs(mid * mid - x) > epsilon) {
          while (e-s > epsilon) {
//          System.out.println("try " + mid);
          mid = s + (e - s) / 2.0;


              if (mid * mid < x) {
                  s = mid;
              } else {
                  e = mid;
              }

      }

      return mid;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RealSquareRoot.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
