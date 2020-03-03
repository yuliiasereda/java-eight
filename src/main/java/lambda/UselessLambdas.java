package lambda;

import java.util.Optional;
import model.QualityAnnotations.Good;
import model.QualityAnnotations.Ugly;

public class UselessLambdas {

  @Ugly("Lambdas are not always the best option")
  class UnneededLambdasUsage {

    private String doProcess(String name) {
      return "MR. " + name;
    }

    public void processAndPrint(String name) {
      Optional.ofNullable(name)
          .map(s -> s.toUpperCase())
          .map(s -> doProcess(s))
          .ifPresent(s -> System.out.println(s));
    }
  }

  @Good("In some case you can replace it with method reference")
  class MethodReferenceUsage {

    private String doProcess(String name) {
      return "MR. " + name;
    }

    public void processAndPrint(String name) {
      Optional.ofNullable(name)
          .map(String::toUpperCase)
          .map(this::doProcess)
          .ifPresent(System.out::print);
    }
  }
}