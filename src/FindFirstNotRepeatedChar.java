import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class FindFirstNotRepeatedChar {


  // first method with LinkedHashMap, requires two iterations.
  public static char getFirstNonRepeatedChar(String str) {
    Map<Character, Integer> counts = new LinkedHashMap<Character, Integer>(str.length());

    for (char c: str.toCharArray()) {
      counts.put(c, counts.containsKey(c) ? counts.get(c) + 1 : 1);
    }
    for (Entry<Character, Integer> entry : counts.entrySet()) {
      if (entry.getValue() == 1) {
        return entry.getKey();
      }
    }
    throw new RuntimeException("Did not find any repeated character.");
  }

  // second method with ArrayList and HashSet to store repeated and non repeated characters.
  // Only needs one pass.

  public static char firstNonRepeatedCharacter(String str) {
    Set<Character> repeating = new HashSet<>();
    List<Character> nonRepeating = new ArrayList<>();

    for (int i = 0; i < str.length(); i++) {
      char letter = str.charAt(i);
      if (repeating.contains(letter)) {
        continue;
      }
      if (nonRepeating.contains(letter)) {
        nonRepeating.remove(letter);
        repeating.add(letter);
      } else {
        nonRepeating.add(letter);
      }
    }
    return nonRepeating.get(0);
  }

  // third method with hashMap.

  public static char firstNonRepeatingCharacter(String str) {
    HashMap<Character, Integer> scoreBoard = new HashMap<>();

    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if (scoreBoard.containsKey(c)) {
        scoreBoard.put(c, scoreBoard.get(c) + 1);
      } else {
        scoreBoard.put(c, 1);
      }
    }
    // since HashMap doesn't maintain the order we need to go through the String again.
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if (scoreBoard.get(c) == 1) {
        return c;
      }
    }
    throw new RuntimeException("Undefined behavior.");
  }
}
