package optional;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

import java.util.Optional;
import model.Classic;
import model.QualityAnnotations.Bad;
import model.QualityAnnotations.Good;
import model.QualityAnnotations.Ugly;

public class PopularCasesWhenGettingInfo {

  @Classic
  class SameOldImperativeStyle {

    public String getPersonCarInsuranceName(Person person) {
      String name = "Unknown";
      if (ofNullable(person).isPresent()) {
        if (person.getCar().isPresent()) {
          if (person.getCar().get().getInsurance().isPresent()) {
            name = person.getCar().get().getInsurance().get().getName();
          }
        }
      }
      return name;
    }
  }

  @Ugly
  class UsingIfPresentInSameImperativeWayWithDirtyHack {

    public String getPersonCarInsuranceName(Person person) {
      final StringBuilder builder = new StringBuilder();
      ofNullable(person).ifPresent(p -> p.getCar()
          .ifPresent(c -> c.getInsurance().ifPresent(i -> builder.append(i.getName()))));
      return builder.toString();
    }
  }

  @Bad
  class UsingMapWithUncheckedGet {

    public String getPersonCarInsuranceName(Person person) {
      return ofNullable(person)
          .map(Person::getCar)
          .map(car -> car.get().getInsurance())
          .map(insurance -> insurance.get().getName())
          .orElse("Unknown");
    }
  }

  @Ugly("Hard to read")
  class UsingMapWithOrElseEmptyObjectToFixUncheckedGet {

    public String getPersonCarInsuranceName(Person person) {
      return ofNullable(person)
          .map(Person::getCar)
          .map(car -> car.orElseGet(Car::new).getInsurance())
          .map(insurance -> insurance.orElseGet(Insurance::new).getName())
          .orElse("Unknown");
    }
  }

  @Good("Readable, understandable")
  class UsingFlatMap {

    public String getCarInsuranceNameFromPersonUsingFlatMap(Person person) {
      return ofNullable(person)
          .flatMap(Person::getCar)
          .flatMap(Car::getInsurance)
          .map(Insurance::getName)
          .orElse("Unknown");
    }
  }


  class Person {

    Optional<Car> getCar() {
      return empty(); //stub
    }
  }

  class Car {

    Optional<Insurance> getInsurance() {
      return empty(); //stub
    }
  }

  class Insurance {

    String getName() {
      return ""; //stub
    }
  }
}
