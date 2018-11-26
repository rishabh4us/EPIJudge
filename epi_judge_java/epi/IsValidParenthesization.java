package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

public class IsValidParenthesization {
  @EpiTest(testDataFile = "is_valid_parenthesization.tsv")

  public static boolean isWellFormed(String s) {
    // TODO - you fill in here.
//    System.out.println(s);
    Map<Character, Character> hm = new HashMap<>();
    hm.put('(',')');
    hm.put('[',']');
    hm.put('{','}');
    ArrayDeque<Character> stack = new ArrayDeque<>();
    for(char c:s.toCharArray()){
      if(hm.keySet().contains(c)) stack.push(c);
      else{
        if(stack.isEmpty() || c != hm.get(stack.pop())) return false;
      }
    }


    return stack.isEmpty();
  }

  public static void main(String[] args) {
//    isWellFormed(")");
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsValidParenthesization.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
