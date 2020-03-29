package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

  @EpiTest(testDataFile = "calendar_rendering.tsv")

  public static int findMaxSimultaneousEvents(List<Event> A) {
    // convert every event to two end-points and create a list of end-points
    List<Endpoint> endpoints = A.stream().map((e) -> Arrays.asList(new Endpoint(e.start, true),
            new Endpoint(e.finish, false)))
            .flatMap(List::stream)
            .collect(Collectors.toList());

    endpoints.sort((a, b) -> {
      if (a.time != b.time) {
        return Integer.compare(a.time, b.time);
      }
      // if time is equal, then endpoint that starts an interval comes first
      return a.isStart && !b.isStart ? -1 : !a.isStart && b.isStart ? 1 : 0;
    });

    int maxSimultaneousEventsPossible = 0, numSimultaneousEvents = 0;
    // process each endpoint and keep a counter for maxSimultaneousEventsPossible.
    for (Endpoint endpoint : endpoints) {
      if (endpoint.isStart) {
        // increment counter for start, recompute max
        numSimultaneousEvents++;
        maxSimultaneousEventsPossible = Math.max(maxSimultaneousEventsPossible, numSimultaneousEvents);
      } else {
        // decrement counter for end
        numSimultaneousEvents--;
      }
    }
    return maxSimultaneousEventsPossible;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CalendarRendering.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
