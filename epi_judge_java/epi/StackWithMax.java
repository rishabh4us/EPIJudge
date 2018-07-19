package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.*;

public class StackWithMax {

  private static class Element{
    Integer val;
    Integer max;
    Element(int v, int max){
      val = v;
      this.max = max;
    }
  }

  public static class Stack {
    Deque<Element> data = new ArrayDeque<>();

    public boolean empty() {
      // TODO - you fill in here.
      return data.isEmpty();
    }
    public Integer max() {
      // TODO - you fill in here.
      if (data.isEmpty()) throw new EmptyStackException();
      return data.peek().max;
    }
    public Integer pop() {
      // TODO - you fill in here.
      return data.pop().val;
    }
    public void push(Integer x) {
      // TODO - you fill in here.
      if (data.isEmpty()){
        data.push(new Element(x,x));
        return;
      }

      data.push(new Element(x, Math.max(x,data.peek().max)));
      return;
    }
  }
  @EpiUserType(ctorParams = {String.class, int.class})
  public static class StackOp {
    public String op;
    public int arg;

    public StackOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }
  }

  @EpiTest(testDataFile = "stack_with_max.tsv")
  public static void stackTest(List<StackOp> ops) throws TestFailure {
    try {
      Stack s = new Stack();
      int result;
      for (StackOp op : ops) {
        switch (op.op) {
        case "Stack":
          s = new Stack();
          break;
        case "push":
          s.push(op.arg);
          break;
        case "pop":
          result = s.pop();
          if (result != op.arg) {
            throw new TestFailure("Pop: expected " + String.valueOf(op.arg) +
                                  ", got " + String.valueOf(result));
          }
          break;
        case "max":
          result = s.max();
          if (result != op.arg) {
            throw new TestFailure("Max: expected " + String.valueOf(op.arg) +
                                  ", got " + String.valueOf(result));
          }
          break;
        case "empty":
          result = s.empty() ? 1 : 0;
          if (result != op.arg) {
            throw new TestFailure("Empty: expected " + String.valueOf(op.arg) +
                                  ", got " + String.valueOf(s));
          }
          break;
        default:
          throw new RuntimeException("Unsupported stack operation: " + op.op);
        }
      }
    } catch (NoSuchElementException e) {
      throw new TestFailure("Unexpected NoSuchElement exception");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StackWithMax.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
