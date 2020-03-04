package lambda;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import model.QualityAnnotations.Ugly;
import model.User;
import model.dto.UserDto;

public class AvoidLongLambdas {

  @Ugly
  class LongLambdaInPlace {

    public List<UserDto> convertToDto(List<User> users) {
      return users.stream().map(user -> {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        //it happens to be much more fields and much more logic in terms of remapping these fields
        return dto;
      })
          .collect(Collectors.toList());
    }
  }

  class MethodReferenceInsteadOfLambda {

    //particular toDto could be implemented as a separate class or as a lambda function
    private final Function<User, UserDto> toDto = this::convertToDto;

    public List<UserDto> convertToDto(List<User> users) {
      return users.stream().map(toDto).collect(Collectors.toList());
    }

    private UserDto convertToDto(User user) {
      UserDto dto = new UserDto();
      dto.setId(user.getId());
      dto.setName(user.getName());
      return dto;
    }

  }

}
