package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;

public class IsAnonymousLetterConstructible {
  @EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")

  public static boolean isLetterConstructibleFromMagazine(String letterText,
                                                          String magazineText) {
    // TODO - you fill in here.

    HashMap<Character,Integer> hm = new HashMap<>();
    for (int i = 0; i<letterText.length(); i++){
      final char c = letterText.charAt(i);
        hm.put(c,hm.getOrDefault(c,0)+1);
    }

    for (int i = 0; i<magazineText.length(); i++){
      final char c = magazineText.charAt(i);

      if(hm.containsKey(c)){
       if( hm.get(c) == 1)
        hm.remove(c);
       else
         hm.put(c,hm.get(c)-1);
      }
    }

    return hm.isEmpty();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsAnonymousLetterConstructible.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
