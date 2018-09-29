package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class EvaluateRpn {
  static Set<String> op = new HashSet<String>(Arrays.asList("+", "/", "-", "*"));



  @EpiTest(testDataFile = "evaluate_rpn.tsv")
  public static int eval(String expression) {
    // TODO - you fill in here.

    String [] ip = expression.split(",");
    ArrayDeque<Integer> stack = new ArrayDeque<>();

    int i = 0;
    while(i<ip.length) {
      if (!op.contains(ip[i])) {
        stack.push(Integer.parseInt(ip[i]));
      } else {

        int b = stack.pop();
        int a = stack.pop();
        switch (ip[i]) {
          case "+":
            stack.push(a + b);
            break;
          case "/":
            stack.push(a / b);
            break;
          case "-":
            stack.push(a - b);
            break;
          case "*":
            stack.push(a * b);
            break;
        }
      }
//      System.out.println(stack.toString());
      i++;
    }
return stack.pop();
//    return calc(ip, ip.length-1);
  }

//  private static int calc(String[] ip, int i) {
//    System.out.println(ip[i]);
//    if(!op.contains(ip[i])) {
//      return Integer.parseInt(ip[i]);
//    }
//
//    int a = calc(ip,i-1);
//    int b = calc(ip, i-2);
//
//    switch(ip[i]) {
//      case "+":
//        return a+b;
//      case "/":
//        return a/b;
//      case "-":
//        return a-b;
//      case "*":
//        return a*b;
//    }
//  return -1;
//  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvaluateRpn.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
