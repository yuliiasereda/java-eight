package lambda;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import model.Permission;
import model.QualityAnnotations.Good;
import model.QualityAnnotations.Ugly;
import model.User;

public class RemoveElementFromCollection {

  private final Set<User> users = new HashSet<>();


  class ManuallyRemoveElementWithIteratorRemove {

    @Ugly
    public void removeUsersWithPermission(Permission permission) {
      Iterator<User> iterator = users.iterator();
      while (iterator.hasNext()) {
        User user = iterator.next();
        if (user.getRoles().stream().anyMatch(r -> r.getPermissions().contains(permission))) {
          iterator.remove();
        }
      }
    }
  }

  @Good("Better: say what to do (e.g. removeIf()) and set the Predicate (condition) not just loop over the list, perform check and then remove")
  class RemoveWithPredicate {

    public void removeUsersWithPermission(Permission permission) {
      users.removeIf(
          user -> user.getRoles().stream().anyMatch(r -> r.getPermissions().contains(permission)));
    }
  }
}
