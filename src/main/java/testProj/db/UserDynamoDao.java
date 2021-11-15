package testProj.db;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

import javax.inject.Inject;
import java.util.UUID;

public class UserDynamoDao implements UserDao<UserDynamoEntity> {
    public final DynamoDBMapper mapper;

    @Inject
    public UserDynamoDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public UserDynamoEntity getUser(UUID uuid) {
//        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
//        DynamoDB dynamoDB = new DynamoDB(client);
//        Table table = dynamoDB.getTable("Users");
//        Item item = table.getItem("userId", uuid.toString());
//
//        System.out.println(item.toJSONPretty());
        return mapper.load(UserDynamoEntity.class, uuid.toString());
    }

    @Override
    public UserDynamoEntity createUser(UserDynamoEntity userDynamoEntity) {
        mapper.save(userDynamoEntity);
        return userDynamoEntity;
    }
}
