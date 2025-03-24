package org.example.test1221systems.mapper;

import org.example.test1221systems.dto.user.UserRequest;
import org.example.test1221systems.dto.user.UserResponse;
import org.example.test1221systems.entity.User;
import org.example.test1221systems.entity.targets.UserTarget;
import org.example.test1221systems.exceptions.InvalidValueException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "id", source = "user.id")
    UserResponse convertFromUserToUserResponse(User user);

    @Mapping(target = "target", qualifiedByName = "analyzeTarget", source = "userResponse.target")
    User convertFromUserRequestToEntity(UserRequest userResponse);

    List<UserResponse> convertFromListUserToListUserResponse(List<User> user);

    @Named("analyzeTarget")
    default UserTarget analyzeTarget(String target) {
        Optional<UserTarget> result = UserTarget.fromString(target);
        if (result.isEmpty()) {
            throw new InvalidValueException(UserTarget.class);
        }

        return result.get();
    }

}
