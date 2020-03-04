package lambda;

import java.util.function.Function;
import java.util.function.UnaryOperator;
import model.QualityAnnotations.Bad;

public class ClassDesign {

  @Bad("The problem is: ambiguous overloaded methods (UnaryOperator extends Function)")
  static class AmbiguousOverloadedMethods {

    interface AmbiguousService<T> {

      <R> R process(Function<T, R> fn);

      T process(UnaryOperator<T> fn);
    }

    public void usage(AmbiguousService<String> service) {
      // which method you intended to call??? both are acceptable. But it calls with UnaryOperator
      service.process(String::toUpperCase);

      // UnaryOperator extends Function. Calls with Function
      Function<String, String> function = String::toUpperCase;
      service.process(function);
    }

    static class SeparateSpecializedMethods {

      interface ClearService<T> {

        <R> R convert(Function<T, R> fn);

        T process(UnaryOperator<T> fn);
      }

      public void usage(ClearService<String> service) {
        //now it's clear which method will be called.
        service.convert(String::toUpperCase);
      }
    }

  }
}
