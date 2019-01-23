package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class IsStringDecomposableIntoWords {

  public static List<String>
  decomposeIntoDictionaryWords(String domain, Set<String> dictionary) {
    // TODO - you fill in here.
    List<String> ans = sol(domain, dictionary, 0, new HashMap<>());
    System.out.println(ans);
    return ans == null ? new ArrayList<>(): ans;
  }

  private static List<String> sol(String ip, Set<String> dictionary, int s, Map<Integer, List<String>> cache) {
    if(cache.get(s) != null) return cache.get(s);

    if(s==ip.length()) return new ArrayList<>();

    for(int i = s ; i<ip.length(); i++){

      String currWord = ip.substring(s, i + 1);
      if(dictionary.contains(currWord)){
        List<String> child = sol(ip,dictionary, i+1, cache);

        if(child == null) return null;
        cache.put(i+1, child);
        child.add(0,currWord);
        return child;
      }
    }

    return null;
  }
  @EpiTest(testDataFile = "is_string_decomposable_into_words.tsv")
  public static void decomposeIntoDictionaryWordsWrapper(TimedExecutor executor,
                                                         String domain,
                                                         Set<String> dictionary,
                                                         Boolean decomposable)
      throws Exception {
    List<String> result =
        executor.run(() -> decomposeIntoDictionaryWords(domain, dictionary));

    if (!decomposable) {
      if (!result.isEmpty()) {
        throw new TestFailure("domain is not decomposable");
      }
      return;
    }

    if (result.stream().anyMatch(s -> !dictionary.contains(s))) {
      throw new TestFailure("Result uses words not in dictionary");
    }

    if (!String.join("", result).equals(domain)) {
      throw new TestFailure("Result is not composed into domain");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringDecomposableIntoWords.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
