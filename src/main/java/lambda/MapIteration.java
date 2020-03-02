package lambda;

import static java.util.stream.Collectors.toMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import model.QualityAnnotations.Good;
import model.QualityAnnotations.Ugly;
import model.User;

public class MapIteration {

  class UsingOldGoodEntrySet {

    @Ugly("Loop over entrySet requires .getKey() and .getValue()")
    public Map<String, String> getUserNames(Map<String, User> users) {
      Map<String, String> userNames = new HashMap<>();
      users.entrySet().forEach(user -> userNames.put(user.getKey(), user.getValue().getName()));
      return userNames;
    }
  }

  class UsingMapForEach {

    @Good("Replaced with loop over map (not entry set)")
    public Map<String, String> getUserNames(Map<String, User> users) {
      Map<String, String> userNames = new HashMap<>();
      users.forEach((key, value) -> userNames.put(key, value.getName()));
      return userNames;
    }
  }

  class UsingMapTransform {

    @Good("Using collectors with entrySet")
    public Map<String, String> getUserNames(Map<String, User> users) {
      return users.entrySet().stream()
          .collect(toMap(Entry::getKey, this::getName));
    }

    private String getName(Entry<String, User> entry) {
      return entry.getValue().getName();
    }
  }

}
