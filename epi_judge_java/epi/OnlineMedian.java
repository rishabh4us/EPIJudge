package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;
import java.util.stream.DoubleStream;

public class OnlineMedian {
  public static List<Double> onlineMedian(Iterator<Integer> sequence) {
    // TODO - you fill in here.

    List <Double> ans = new ArrayList<>();
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(10, new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o2-o1;
      }
    });

////    for first element
//    if(sequence.hasNext()){
//      int n = sequence.next();
//      maxHeap.add(n);
//      ans.add((double) n);
//    }
    while(sequence.hasNext()){
      int n = sequence.next();
      maxHeap.add(n);
      minHeap.add(maxHeap.remove());
      maxHeap.add(minHeap.remove());
      if(maxHeap.size()-minHeap.size()>1) {
        minHeap.add(maxHeap.remove());
      }
      ans.add(maxHeap.size()==minHeap.size()? (maxHeap.peek()+minHeap.peek())/2.0 : (double)maxHeap.peek());
    }
    return ans;
  }
  @EpiTest(testDataFile = "online_median.tsv")
  public static List<Double> onlineMedianWrapper(List<Integer> sequence) {
    return onlineMedian(sequence.iterator());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "OnlineMedian.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
