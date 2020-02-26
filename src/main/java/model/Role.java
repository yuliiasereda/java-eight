package model;

import java.util.EnumSet;
import java.util.Set;
import lombok.Data;

@Data
public class Role {
private String name;
private Set<Permission> permissions = EnumSet.noneOf(Permission.class);
}
