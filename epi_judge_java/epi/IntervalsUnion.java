package epi;
import com.sun.corba.se.spi.activation.EndPointInfo;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class IntervalsUnion {

  public static class Interval {
    public Endpoint left = new Endpoint();
    public Endpoint right = new Endpoint();

    private static class Endpoint {
      public boolean isClosed;
      public int val;
    }
  }

  public static List<Interval> unionOfIntervals(List<Interval> intervals) {

    // empty list
    if (intervals.isEmpty()) {
      return Collections.EMPTY_LIST;
    }

    // sort the intervals
    intervals.sort((a,b) -> {
      if (Integer.compare(a.left.val, b.left.val) != 0) {
        return a.left.val - b.left.val;
      }
      if (a.left.isClosed && !b.left.isClosed) {
        return -1;
      }
      return !a.left.isClosed && b.left.isClosed ? 1 : 0;
    });

    List<Interval> result = new ArrayList<>();
    result.add(intervals.get(0));
    int i = 1;
    while (i < intervals.size()) {
      if (intervals.get(i).left.val > result.get(i-1).right.val) {
        result.add(intervals.get(i));
        i++;
      } else {
        if (result.get(i-1).left.val < intervals.get(i).left.val) {
          result.get(i-1).left.isClosed = result.get(i-1).left.isClosed;
        } else if (result.get(i-1).left.val > intervals.get(i).left.val) {
          result.get(i-1).left.isClosed = intervals.get(i).left.isClosed;
        } else {
          result.get(i-1).left.isClosed = result.get(i-1).left.isClosed || intervals.get(i).left.isClosed;
        }

        if (result.get(i-1).right.val < intervals.get(i).right.val) {
          result.get(i-1).right.isClosed = intervals.get(i).right.isClosed;
        } else if (result.get(i-1).right.val > intervals.get(i).right.val) {
          result.get(i-1).right.isClosed = result.get(i-1).right.isClosed;
        } else {
          result.get(i-1).right.isClosed = result.get(i-1).right.isClosed || intervals.get(i).right.isClosed;
        }

        result.get(i-1).left.val = Math.min(result.get(i-1).left.val, intervals.get(i).left.val);
        result.get(i-1).right.val = Math.max(result.get(i-1).right.val, intervals.get(i).right.val);
        i++;
      }
    }


    return result;
  }
  @EpiUserType(
      ctorParams = {int.class, boolean.class, int.class, boolean.class})
  public static class FlatInterval {
    int leftVal;
    boolean leftIsClosed;
    int rightVal;
    boolean rightIsClosed;

    public FlatInterval(int leftVal, boolean leftIsClosed, int rightVal,
                        boolean rightIsClosed) {
      this.leftVal = leftVal;
      this.leftIsClosed = leftIsClosed;
      this.rightVal = rightVal;
      this.rightIsClosed = rightIsClosed;
    }

    public FlatInterval(Interval i) {
      if (i != null) {
        leftVal = i.left.val;
        leftIsClosed = i.left.isClosed;
        rightVal = i.right.val;
        rightIsClosed = i.right.isClosed;
      }
    }

    public Interval toInterval() {
      Interval i = new Interval();
      i.left.val = leftVal;
      i.left.isClosed = leftIsClosed;
      i.right.val = rightVal;
      i.right.isClosed = rightIsClosed;
      return i;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      FlatInterval that = (FlatInterval)o;

      if (leftVal != that.leftVal) {
        return false;
      }
      if (leftIsClosed != that.leftIsClosed) {
        return false;
      }
      if (rightVal != that.rightVal) {
        return false;
      }
      return rightIsClosed == that.rightIsClosed;
    }

    @Override
    public int hashCode() {
      int result = leftVal;
      result = 31 * result + (leftIsClosed ? 1 : 0);
      result = 31 * result + rightVal;
      result = 31 * result + (rightIsClosed ? 1 : 0);
      return result;
    }

    @Override
    public String toString() {
      return "" + (leftIsClosed ? "<" : "(") + leftVal + ", " + rightVal +
          (rightIsClosed ? ">" : ")");
    }
  }

  @EpiTest(testDataFile = "intervals_union.tsv")
  public static List<FlatInterval>
  unionIntervalWrapper(TimedExecutor executor, List<FlatInterval> intervals)
      throws Exception {
    List<Interval> casted = new ArrayList<>(intervals.size());
    for (FlatInterval in : intervals) {
      casted.add(in.toInterval());
    }

    List<Interval> result = executor.run(() -> unionOfIntervals(casted));

    intervals = new ArrayList<>(result.size());
    for (Interval i : result) {
      intervals.add(new FlatInterval(i));
    }
    return intervals;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntervalsUnion.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
