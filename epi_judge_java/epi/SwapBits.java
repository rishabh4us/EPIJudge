package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SwapBits {
  @EpiTest(testDataFile = "swap_bits.tsv")
  public static long swapBits(long x, int i, int j) {

    // extract the ith and jth bits and see if they differ. we need to swap them only if they differ
    if (((x >>> i) & 1) != ((x >>> j) & 1)) {
      //create bit masks and then just XOR with x
      long bitMask = (1L << i) | (1L << j);
      x ^= bitMask;
    }
    return x;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SwapBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
