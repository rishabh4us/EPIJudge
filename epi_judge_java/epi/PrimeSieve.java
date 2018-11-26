package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class PrimeSieve {
  @EpiTest(testDataFile = "prime_sieve.tsv")
  // Given n, return all primes up to and including n.
  public static List<Integer> generatePrimes(int n) {
      // TODO - you fill in here.
    return faster_moreSpace(n);

//      return slower_lessSpace(n);

  }

    private static List<Integer> slower_lessSpace(int n) {
        List<Integer> ans = new ArrayList<>();
        if (n < 2) return ans;


        for (int i = 2; i < n + 1; i++) {
            int limit = (int) Math.sqrt(i);
            boolean isPrime = true;
            for (int j = 2; j <= limit; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;


                }
            }
            if (isPrime) ans.add(i);
        }


        return ans;
    }

    private static List<Integer> faster_moreSpace(int n) {
    List<Integer> ans = new ArrayList<>();
    if (n<2) return ans;

    boolean dp[] = new boolean[n+1];
    for (int i = 0; i<n+1; i++){dp[i] = true;}
    dp[0]= false;
    dp[1] = false;
    for (int i = 0; i<n+1; i++){

      if(dp[i]){
        ans.add(i);
        for (int j=i+i; j<n+1; j=j+i){
          dp[j] = false;
        }
      }

    }
    return ans;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimeSieve.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
