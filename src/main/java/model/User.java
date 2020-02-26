package model;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class User {
private Long id;
private String name;
private int age;
private Set<Role> roles = new HashSet<>();
}
