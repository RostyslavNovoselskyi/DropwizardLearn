package testProj.db;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;

import java.util.UUID;

@DynamoDBTable(tableName = "Users")
public class UserDynamoEntity {

    private String userId;
    private String userName;

    @DynamoDBHashKey(attributeName="userId")
    public String getUserId() { return userId; }

    public void setId(String userId) {this.userId = userId; }

    @DynamoDBAttribute(attributeName="userName")
    public String getUserName() {return userName; }

    public void setUserName(String userName) { this.userName = userName; }

}
