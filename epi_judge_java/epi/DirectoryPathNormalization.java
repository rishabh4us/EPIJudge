package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class DirectoryPathNormalization {
  @EpiTest(testDataFile = "directory_path_normalization.tsv")

  public static String shortestEquivalentPath(String path) {
    // TODO - you fill in here.
    System.out.println();
    System.out.println(path);
    String[] ip = path.split("/");
    Deque<String> stack = new ArrayDeque<>();
    if(path.charAt(0) == '/') stack.push("/");
    for( String s : ip){
       if(s.equals(".") || s.equals("")){
        continue;
      }
      else  if(!(s.equals(".") || s.equals(".."))){
        stack.push(s);
      } else if(s.equals("..")){

        if(!stack.isEmpty() && !stack.peek().equals(s))stack.pop();
        else stack.push(s);

      }
    }

    StringBuffer sb = new StringBuffer();
    Iterator<String> iter = stack.descendingIterator();
    sb.append(iter.next());

    while (iter.hasNext()){
     if(sb.charAt(sb.length()-1) != '/') sb.append("/");
      sb.append(iter.next());
    }

    String s = sb.toString();
    System.out.println(s);
    return s;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DirectoryPathNormalization.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
