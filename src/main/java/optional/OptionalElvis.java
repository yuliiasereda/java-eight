package optional;

import static java.util.Optional.ofNullable;

import model.Classic;
import model.QualityAnnotations.Ugly;
import model.User;

public class OptionalElvis {

  @Classic
  class BeforeJava8 {

    public String getUserName(User user) {
      return (user != null && user.getName() != null) ? user.getName() : "default";
    }
  }

  @Ugly("So many actions just to get 'name' safely")
  class UsingOptionalIsPresent {

    public String getUserName(User user) {
      if (ofNullable(user).isPresent()) {
        if (ofNullable(user.getName()).isPresent()) {
          return user.getName();
        }
      }
      return "default";
    }
  }

  class UsingOrElse {

    String getUserName(User user) {
      return ofNullable(user)
          .map(User::getName)
          .orElse("default");
    }
  }
}
