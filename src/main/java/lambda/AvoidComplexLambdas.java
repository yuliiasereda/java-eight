package lambda;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import model.Permission;
import model.QualityAnnotations.Good;
import model.QualityAnnotations.Ugly;
import model.Role;
import model.User;

public class AvoidComplexLambdas {

  private final Set<User> users = new HashSet<>();

  @Ugly("Nested lambdas can be extracted")
  class UsingComplexLambdasInPlace {

    public Set<User> findEditors() {
      return users.stream()
          .filter(u -> u.getRoles().stream().anyMatch(r -> r.getPermissions().contains(
              Permission.EDIT))).collect(Collectors.toSet());
    }
  }

  @Good("In this case there are two ways to do that:" +
      "1 - extract to hasPermission(...) if argument is required" +
      "2 - extract to hasEditPermission() if no arguments required (can be used method reference)")
  class ComplexityExtractedToMethodReference {

    public Set<User> checkPermission(Permission permission) {
      return users.stream()
//          .filter(this::hasEditPermission)
          .filter(hasPermission(Permission.EDIT))
          .collect(Collectors.toSet());
    }


    private Predicate<User> hasPermission(Permission permission) {
      return user -> user.getRoles().stream().map(Role::getPermissions)
          .anyMatch(permissions -> permissions.contains(permission));
    }

    private boolean hasEditPermission(User user) {
      return hasPermission(Permission.EDIT).test(user);
    }
  }
}
