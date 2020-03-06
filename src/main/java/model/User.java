package model;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class User {
private Long id;
private String name;
private int age;
private Set<Role> roles = new HashSet<>();

  public User(Long id, String name, int age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }
}
