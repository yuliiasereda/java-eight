package lambda;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import model.QualityAnnotations.Ugly;
import model.User;

//Multi MAP - simple map, where value is collection
public class EmulateMultimap {

  private final Map<String, Set<User>> usersByRoleName = new HashMap<>(); //role-users

  @Ugly("Code is not very readable, null check when retrieve set from map and when trying to add item to set in map")
  class LegacyCreationOnFirstValueForTheKey {

    public void addUser(User user) {
      user.getRoles().forEach(r -> {
        Set<User> usersInRole = usersByRoleName.get(r.getName());//get set of users by key(role)
        if (usersInRole == null) { //no users with this role
          usersInRole = new HashSet<>();
//          usersByRoleName.put(r.getName(), usersInRole);
        }
        usersInRole.add(user);
        usersByRoleName.put(r.getName(), usersInRole);
      });
    }

    public Set<User> getUsersInRole(String role) {
      Set<User> users = usersByRoleName.get(role);
      return users == null ? Collections.emptySet() : users;
    }
  }

  class ComputeEmptySetIfKeyIsAbsent {

    public void addUser(User user) {
      user.getRoles().forEach(
          r -> usersByRoleName.computeIfAbsent(r.getName(), k -> new HashSet<>()).add(user));
    }
  }

  public Set<User> getUsersInRole(String role) {
    return usersByRoleName.getOrDefault(role, Collections.emptySet());
  }
}
