package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.List;
public class MatrixConnectedRegions {
  public static void flipColor(int x, int y, List<List<Boolean>> image) {
    // TODO - you fill in here.

    sol(x,y,image, image.get(x).get(y));
    return;
  }
static class Node {
    int x;
    int y;
    Node(int x, int y){
      this.x = x;
      this.y = y;
    }
}
  private static void sol(int x, int y, List<List<Boolean>> image, boolean color) {

    if(image.get(x).get(y) == color) {
      image.get(x).set(y, !color);
    }
    List<Node> adj = getAdj(x,y,image,color);
    for(Node n : adj){
      sol(n.x,n.y,image, color);
    }

  }

  private static List<Node> getAdj(int x, int y, List<List<Boolean>> image, boolean color) {

    List<Node> a = new ArrayList<>();
    if (x + 1 < image.size() && image.get(x + 1).get(y) == color) a.add(new Node(x + 1, y));
    if (y + 1 < image.get(x).size() && image.get(x).get(y + 1) == color) a.add(new Node(x, y + 1));
    if (x > 0 && image.get(x - 1).get(y) == color) a.add(new Node(x - 1, y));
    if (y > 0 && image.get(x).get(y - 1) == color) a.add(new Node(x, y - 1));

    return a;
  }

  @EpiTest(testDataFile = "painting.tsv")
  public static List<List<Integer>> flipColorWrapper(TimedExecutor executor,
                                                     int x, int y,
                                                     List<List<Integer>> image)
      throws Exception {
    List<List<Boolean>> B = new ArrayList<>();
    for (int i = 0; i < image.size(); i++) {
      B.add(new ArrayList<>());
      for (int j = 0; j < image.get(i).size(); j++) {
        B.get(i).add(image.get(i).get(j) == 1);
      }
    }

    executor.run(() -> flipColor(x, y, B));

    image = new ArrayList<>();
    for (int i = 0; i < B.size(); i++) {
      image.add(new ArrayList<>());
      for (int j = 0; j < B.get(i).size(); j++) {
        image.get(i).add(B.get(i).get(j) ? 1 : 0);
      }
    }

    return image;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MatrixConnectedRegions.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
