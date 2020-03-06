package optional;

import java.util.Optional;
import model.QualityAnnotations.Good;
import model.QualityAnnotations.Ugly;
import model.User;

public class TellDontAsk {

  class ManualCheckForPresenceToThrowException {

    @Ugly("Redundant check 'isPresent()'")
    public String getUserName(Long userId) {
      Optional<User> user = findById(userId);
      if (user.isPresent()) {
        return user.get().getName();
      }
      throw new IllegalStateException("User not found");
    }

    public void deleteUser(Long userId) {
      Optional<User> user = findById(userId);
      if (user.isPresent()) {
        delete(user.get());
      }
    }

    private void delete(User user) {
      //delete from db
    }
  }

  @Good("Better say what todo, do not ask 'if ok, then do'")
  class OrElseThrowUsage {

    public String getUserName(Long userId) {
      return findById(userId)
          .orElseThrow(() -> new IllegalStateException("User not found"))
          .getName();
    }

    public void deleteUser(Long userId) {
      findById(userId).ifPresent(this::delete);
    }

    private void delete(User user) {
      //delete from db
    }
  }

  private Optional<User> findById(Long userId) {
    //search in db
    return Optional.of(new User(1L, "Sereda", 18));
  }
}
