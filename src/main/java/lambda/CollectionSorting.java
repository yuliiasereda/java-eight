package lambda;

import java.util.Comparator;
import java.util.List;
import model.QualityAnnotations.Good;
import model.QualityAnnotations.Ugly;
import model.User;

public class CollectionSorting {

  @Ugly("Can be simplified with 'comparing' method and method reference")
  class UsingLegacyComparator {

    public void sortUsers(List<User> users) {
      users.sort((x, y) -> Long.compare(x.getId(), y.getId()));
    }
  }

  @Good("It looks shorted and more readable")
  class UsingExistingPredefinedComparator {

    public void sortUsersById(List<User> users) {
      //Can be used 'comparingLong(User::getId)'
      users.sort(Comparator.comparing(User::getId));
    }
  }
}
