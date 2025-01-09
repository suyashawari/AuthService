package org.auth.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.auth.entities.UserInfo;
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserInfoDto  extends UserInfo {

     private String userName;
     private String lastName;
     private String email;
     private long phoneNumber;
}
