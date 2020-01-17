package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import java.util.List;
import java.util.NoSuchElementException;

public class CircularQueue {

  public static class Queue {

    private int head = 0, tail = 0, numQueueElements = 0;
    private Integer[] entries;
    private int SCALE_FACTOR = 2;

    public Queue(int capacity) {
      entries = new Integer[capacity];
    }

    public void enqueue(Integer x) {
      if (numQueueElements == entries.length) {
        // Need to resize the array
        Integer[] temp = new Integer[entries.length*SCALE_FACTOR];

        for (int i = 0; i < entries.length; ) {
          temp[i] = entries[head];
          i++;
          head = (head+1) % entries.length;
        }
        head = 0;
        tail = numQueueElements;
        entries = temp;
      }
      entries[tail] = x;
      tail = (tail+1) % entries.length;
      ++numQueueElements;
      return;
    }

    public Integer dequeue() {
      Integer result = 0;
      if (numQueueElements != 0) {
        numQueueElements--;
        result = entries[head];
        head = (head+1) % entries.length;
        return result;
      }
      throw new NoSuchElementException("Deque called on an empty queue");
    }

    public int size() {
      return numQueueElements;
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
