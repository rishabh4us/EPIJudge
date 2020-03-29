package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class DirectoryPathNormalization {
  @EpiTest(testDataFile = "directory_path_normalization.tsv")

  public static String shortestEquivalentPath(String path) {
    if (path.equals("")) {
      throw new IllegalArgumentException("empty path");
    }

    // starts with '/' which is root. cannot go above root.

    // split on '/'


    // handle '..' , remove from stack unless it is '/'
    // no-op on '.' and ''
    // add directory or file name to string
    return "";
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DirectoryPathNormalization.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
