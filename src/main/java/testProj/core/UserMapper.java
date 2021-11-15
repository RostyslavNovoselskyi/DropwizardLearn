package testProj.core;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import testProj.api.User;
import testProj.db.UserDynamoEntity;
import testProj.db.UserEntity;

@Mapper
public interface UserMapper {

    @Mapping(target = "name", source = "userName")
    User entityToUser(UserEntity userEntity);

//    @Mapping(target = "id", expression = "java(user.getId.toString())")
////    @Mapping(target = "userId", source = "id")
//    @Mapping(target = "userName", source = "name")
//    UserDynamoEntity userToDynamo(User user);

    @Mapping(target = "id", expression = "java( java.util.UUID.fromString(userDynamo.getUserId()))")
    @Mapping(target = "name", source = "userName")
    User dynamoToUser(UserDynamoEntity userDynamo);

    @Mapping(target = "userName", source = "name")
    UserEntity userToEntity(User user);
}
