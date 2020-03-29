package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Stack;

public class IsValidParenthesization {
  @EpiTest(testDataFile = "is_valid_parenthesization.tsv")

  public static boolean isWellFormed(String s) {
    Stack<Character> wellFormedness = new Stack<Character>();

    for (char a : s.toCharArray()) {
      // push a value equal to corresponding closing brace when opening brace encountered
      switch(a) {
        case '{':
          wellFormedness.push('}');
          break;
        case '(':
          wellFormedness.push(')');
          break;
        case '[':
          wellFormedness.push(']');
          break;
        case '}':
          if (wellFormedness.isEmpty() || wellFormedness.pop() != '}')
            return false;
          break;
        case ')':
          if (wellFormedness.isEmpty() || wellFormedness.pop() != ')')
            return false;
          break;
        case ']':
          if (wellFormedness.isEmpty() || wellFormedness.pop() != ']')
            return false;
          break;
        default:
          break;
      }

      // pop the value and compare when closing brace encountered. here, if mismatch, return false.
    }

    // if stack is empty return true
    if (wellFormedness.isEmpty())
      return true;
    else
      return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsValidParenthesization.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
