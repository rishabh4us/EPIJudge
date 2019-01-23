package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class ABSqrt2 {
  @EpiTest(testDataFile = "a_b_sqrt2.tsv")

//  class Node {
//    int a;
//    int b;
//    double v ;
//    Node (int x, int y){
//      a= x;
//      b=y;
//      v = a + b*Math.sqrt(2);
//    }
//  }
  public static List<Double> generateFirstKABSqrt2(int k) {
    // TODO - you fill in here.
//    SortedSet<Node> = new TreeSet<Node>()
//    List<Double> ans = new ArrayList<>();
//    int counter = 0 ;
//    while ( counter < k){
//
//      counter ++;
//    }
    return null;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ABSqrt2.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
