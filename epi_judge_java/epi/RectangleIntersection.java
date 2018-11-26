package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
public class RectangleIntersection {
  @EpiUserType(ctorParams = {int.class, int.class, int.class, int.class})
  public static class Rectangle {
    int x, y, width, height;

    public Rectangle(int x, int y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Rectangle rectangle = (Rectangle)o;

      if (x != rectangle.x) {
        return false;
      }
      if (y != rectangle.y) {
        return false;
      }
      if (width != rectangle.width) {
        return false;
      }
      return height == rectangle.height;
    }

    @Override
    public int hashCode() {
      int result = x;
      result = 31 * result + y;
      result = 31 * result + width;
      result = 31 * result + height;
      return result;
    }

    @Override
    public String toString() {
      return "[" + x + ", " + y + ", " + width + ", " + height + "]";
    }
  }
  @EpiTest(testDataFile = "rectangle_intersection.tsv")
  public static Rectangle intersectRectangle(Rectangle r1, Rectangle r2) {
    // TODO - you fill in here.
    if(isIntersecting(r1,r2)){
      int newX = Math.max(r1.x, r2.x);
      int newY = Math.max(r1.y, r2.y);
      return new Rectangle(newX, newY,
              Math.min(r1.x+r1.width,r2.x+r2.width) - newX,
              Math.min(r1.y+r1.height, r2.y+r2.height) - newY);
    }

    return new Rectangle(0, 0, -1, -1);
  }

  private static boolean isIntersecting(Rectangle r1, Rectangle r2) {
    // if right edge is of one is before start of another
    // OR if bottom edge of 1 is above top edge of another

    if(r1.x+r1.width < r2.x || r2.x+r2.width < r1.x) return false;
    if(r1.y+r1.height < r2.y || r2.y+r2.height < r1.y) return false;

    return true;
   }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RectangleIntersection.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
