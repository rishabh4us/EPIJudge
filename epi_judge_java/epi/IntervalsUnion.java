package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    // TODO - you fill in here.

    Collections.sort(intervals, new Comparator<Interval>(){
      public int compare(Interval a, Interval b) {
//        if(a.left.val == b.left.val && a.right.val == b.right.val){
//          if(a.left.isClosed == b.left.isClosed) return a.right.isClosed ? -1 : 1;
//          else return b.left.isClosed ? -1 : 1;
//        }
        if(a.left.val != b.left.val)
          return a.left.val - b.left.val;


        if(a.left.isClosed && ! b.left.isClosed) return -1;
        else if(!a.left.isClosed && b.left.isClosed) return 1;
        else return 0;

//        return a.right.val - b.right.val;

      }
    });

    List<Interval> res = new ArrayList<>();
    Interval curr = intervals.get(0);
    int j = 0;
    while (j < intervals.size()) {


      // if there is overlap
      Interval item = intervals.get(j);
      if (
              (curr.right.val > item.left.val) ||
                      (curr.right.val == item.left.val &&
                              (curr.right.isClosed | item.left.isClosed)
                      )
      ) {

        // handle open close -- for end of interval
        if (curr.right.val < item.right.val) {

          curr.right.val = item.right.val;
          curr.right.isClosed = item.right.isClosed;

        }else if (curr.right.val == item.right.val) {

          curr.right.isClosed = curr.right.isClosed | item.right.isClosed;

        }

        // handle open close of start of interval if same // not needed of we sort on all closed intervals first
        if(curr.left.val == item.left.val){
          curr.left.isClosed = curr.left.isClosed | item.left.isClosed;
        }

      }
      else {
        res.add(curr);
        curr = item;
      }

      j++;
    }
    res.add(curr);

    return res;
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
