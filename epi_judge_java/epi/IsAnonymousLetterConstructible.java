package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Map;

public class IsAnonymousLetterConstructible {
  @EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")

  public static boolean isLetterConstructibleFromMagazine(String letterText,
                                                          String magazineText) {
    Map<Character, Long>  charactersInLetter = new HashMap<>();

    //parse over letter text
    for (int i = 0; i < letterText.length(); i++) {
      Character c = letterText.charAt(i);
      charactersInLetter.putIfAbsent(c, 0L);
      charactersInLetter.put(c, charactersInLetter.get(c) + 1);
    }

    // pass over magazine text
    for (int j = 0; j < magazineText.length(); j++) {
      Character m = magazineText.charAt(j);
      if (charactersInLetter.containsKey(m)) {
        charactersInLetter.put(m, charactersInLetter.get(m) - 1);

        if (charactersInLetter.get(m) == 0L) {
          charactersInLetter.remove(m);
        }

        if (charactersInLetter.isEmpty()) {
          return true;
        }
      }

    }

    return charactersInLetter.isEmpty();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsAnonymousLetterConstructible.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
