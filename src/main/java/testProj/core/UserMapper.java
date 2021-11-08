package testProj.core;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import testProj.api.User;
import testProj.db.UserEntity;

@Mapper
public interface UserMapper {

    @Mapping(target = "name", source = "userName")
    User entityToUser(UserEntity userEntity);

    @Mapping(target = "userName", source = "name")
    UserEntity userToEntity(User user);
}
