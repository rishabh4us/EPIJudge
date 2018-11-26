package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IntSquareRoot {
  @EpiTest(testDataFile = "int_square_root.tsv")

  public static int squareRoot(int x) {
    // TODO - you fill in here.



    // TODO - you fill in here.
    System.out.println("input" + x);
    double s = 0;
    double e = x;
    double epsilon = .00000001;
    double n = x;

    if(x<1) {
      s = x;
      e = 1;
    }

    if (Math.abs(x - 0) < epsilon) return 0;

    while (Math.abs(n * n - x) > epsilon) {
      System.out.println("try " + n);
      n = s + (e - s) / 2.0;


      if (n * n < x) {
        s = n;
      } else {
        e = n;
      }

    }

    return (int)n;


//    return 0;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntSquareRoot.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
