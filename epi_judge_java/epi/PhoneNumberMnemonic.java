package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiPredicate;
public class PhoneNumberMnemonic {
  static String [] map = {"0","1","ABC","DEF","GHI","JKL","MNO","PQRS","TUV","WXYZ"};


  @EpiTest(testDataFile = "phone_number_mnemonic.tsv")
  public static List<String> phoneMnemonic(String phoneNumber) {
    // TODO - you fill in here.
    List<String> ans = new ArrayList<>();
//    System.out.println(phoneNumber);
    helper(phoneNumber.trim(), 0, ans, new StringBuilder() );
    return ans;
  }

  private static void helper(String phoneNumber, int start, List<String> ans, StringBuilder sb) {
    if(start == phoneNumber.length()){
      ans.add(sb.toString());
      return;
    }

    String currOptions = map[phoneNumber.charAt(start) - '0'];
//    System.out.println("curr options: "+currOptions);
    for (char c : currOptions.toCharArray()){
      sb.append(c);
      helper(phoneNumber,start+1,ans,sb);
      sb.deleteCharAt(sb.length()-1);
    }
  }

  @EpiTestComparator
  public static BiPredicate<List<String>, List<String>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PhoneNumberMnemonic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
