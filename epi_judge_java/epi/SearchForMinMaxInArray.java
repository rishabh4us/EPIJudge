package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import java.util.List;
public class SearchForMinMaxInArray {
  @EpiUserType(ctorParams = {Integer.class, Integer.class})

  public static class MinMax {
    public Integer smallest;
    public Integer largest;

    public MinMax(Integer smallest, Integer largest) {
      this.smallest = smallest;
      this.largest = largest;
    }

    private static MinMax minMax(Integer a, Integer b) {
      return Integer.compare(b, a) < 0 ? new MinMax(b, a) : new MinMax(a, b);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      MinMax minMax = (MinMax)o;

      if (!smallest.equals(minMax.smallest)) {
        return false;
      }
      return largest.equals(minMax.largest);
    }

    @Override
    public String toString() {
      return "min: " + smallest + ", max: " + largest;
    }
  }

  @EpiTest(testDataFile = "search_for_min_max_in_array.tsv")

  public static MinMax findMinMax(List<Integer> A) {
    // TODO - you fill in here.

    MinMax m = new MinMax(A.get(0), A.get(0));

    for (int i=0; i<A.size() -1; i+=2){
      if(A.get(i)<A.get(i+1)){
        m.smallest = Math.min(m.smallest, A.get(i));
        m.largest = Math.max(m.largest,A.get(i+1));
      }
      else {
        m.smallest = Math.min(m.smallest, A.get(i+1));
        m.largest = Math.max(m.largest,A.get(i));
      }
    }

    if(A.size()%2 != 0){
      m.smallest = Math.min(m.smallest, A.get(A.size()-1));
      m.largest = Math.max(m.largest,A.get(A.size()-1));
    }
    return m;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchForMinMaxInArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
