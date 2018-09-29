package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.List;
import java.util.NoSuchElementException;

public class CircularQueue {

  public static class Queue {

    int head;
    int tail;
    int numElements;
    int[] items;
    int capacity;

    public Queue(int capacity) {
      items = new int[capacity];
      head = 0;
      tail = 0;
      numElements = 0;
      this.capacity = capacity;
    }

    public void enqueue(Integer x) {
      // TODO - you fill in here.

      if (numElements == capacity) {
        // double & move
        System.out.println("doubling array");
        int newCapacity = 2 * capacity;

        int[] newArr = new int[newCapacity];
        for (int i = 0; i < numElements; i++) {
          newArr[i] = items[head];
          head = (head + 1) % items.length;
        }
        head = 0;
        tail = numElements;
        items = newArr;
        capacity = newCapacity;
      }
      items[tail] = x;
      tail = (tail + 1) % items.length;
      numElements++;

      return;
    }

    public Integer dequeue() {
      // TODO - you fill in here.
      if (numElements == 0) throw new NoSuchElementException();

      int ans = items[head];
      head = (head + 1) % items.length;
      numElements--;

      return ans;
    }

    public int size() {
      // TODO - you fill in here.
      return numElements;
    }

    @Override
    public String toString() {
      // TODO - you fill in here.
      return super.toString();
    }
  }
  @EpiUserType(ctorParams = {String.class, int.class})
  public static class QueueOp {
    public String op;
    public int arg;

    public QueueOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }

    @Override
    public String toString() {
      return op;
    }
  }

  @EpiTest(testDataFile = "circular_queue.tsv")
  public static void queueTest(List<QueueOp> ops) throws TestFailure {
    Queue q = new Queue(1);
    int opIdx = 0;
    for (QueueOp op : ops) {
      switch (op.op) {
      case "Queue":
        q = new Queue(op.arg);
        break;
      case "enqueue":
        q.enqueue(op.arg);
        break;
      case "dequeue":
        int result = q.dequeue();
        if (result != op.arg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, q)
              .withProperty(TestFailure.PropertyName.COMMAND, op)
              .withMismatchInfo(opIdx, op.arg, result);
        }
        break;
      case "size":
        int s = q.size();
        if (s != op.arg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, q)
              .withProperty(TestFailure.PropertyName.COMMAND, op)
              .withMismatchInfo(opIdx, op.arg, s);
        }
        break;
      }
      opIdx++;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CircularQueue.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
