package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class CalendarRendering {
  @EpiUserType(ctorParams = {int.class, int.class})

  public static class Event {
    public int start, finish;

    public Event(int start, int finish) {
      this.start = start;
      this.finish = finish;
    }
  }

  private static class Endpoint {
    public int time;
    public boolean isStart;

    Endpoint(int time, boolean isStart) {
      this.time = time;
      this.isStart = isStart;
    }
  }

  private static class MyComp implements Comparator<Endpoint> {
    public int compare(Endpoint i, Endpoint j) {
      if (i == null || j == null) return 1;
      if (i.time == j.time) {
        if (i.isStart && !j.isStart) return -1;
        if (!i.isStart && j.isStart) return 1;
        return 0;
      }
      return i.time - j.time;
    }
  }


  @EpiTest(testDataFile = "calendar_rendering.tsv")

  public static int findMaxSimultaneousEvents(List<Event> A) {
    // TODO - you fill in here.

    List<Endpoint> e = new ArrayList<>();

    for (Event a : A) {
      e.add(new Endpoint(a.start, true));
      e.add(new Endpoint(a.finish, false));
    }
    e.sort(new MyComp());


    int max = 0, curr = 0;
    for (Endpoint x : e) {
      System.out.println("time " + x.time + "start " + x.isStart);
      if (x.isStart) {
        curr++;

        max = Math.max(curr, max);

      } else {
        curr--;
      }
    }


    return max;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CalendarRendering.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
