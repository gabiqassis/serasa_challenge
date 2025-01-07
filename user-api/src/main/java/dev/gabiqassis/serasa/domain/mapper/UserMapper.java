package dev.gabiqassis.serasa.domain.mapper;

import dev.gabiqassis.serasa.domain.entity.User;
import dev.gabiqassis.serasa.domain.request.UserCreaterRequest;
import dev.gabiqassis.serasa.domain.request.UserUpdateRequest;
import dev.gabiqassis.serasa.domain.response.UserResponse;
import dev.gabiqassis.serasa.domain.response.UserUpdateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User map(UserCreaterRequest userCreateRequest);

    UserResponse map(User user);

    UserUpdateResponse mapToUpdate(User order);

    void map (UserUpdateRequest userUpdateRequest, @MappingTarget User user);
}