package lambda;

import java.util.Set;
import java.util.function.Supplier;
import model.QualityAnnotations.Ugly;
import model.User;

public class LazyCalculationsImprovePerformance {

  @Ugly
  static class LoggingWithAdditionalCheckToAvoidCalculations {

    private static final Log LOG = null;

    public void sendWelcomeEmailToUsers(Set<User> users) {
      //send email
      if (LOG.isDebugEnabled()) {
        LOG.debug("Email have been sent to users: " + users);
      }
    }

    interface Log {

      void debug(String message);

      boolean isDebugEnabled();
    }
  }

  static class PassLambdaToLazyCalculateValueForLogMessage {

    private static final Log LOG = null;

    public void sendWelcomeEmailToUsers(Set<User> users) {
      LOG.debug(() -> "Email have been sent to users: " + users);
    }

    interface Log {

      void debug(String message);

      boolean isDebugEnabled();

      default void debug(Supplier<String> message) {
        if (isDebugEnabled()) {
          debug(message.get());
        }
      }
    }

    String getUserString(Set<User> users) {
      System.out.println("Converting users to string");
      return users.toString();
    }
  }
}
