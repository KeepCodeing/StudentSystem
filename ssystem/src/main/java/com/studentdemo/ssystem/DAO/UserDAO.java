package com.studentdemo.ssystem.DAO;

import com.studentdemo.ssystem.POJO.UserPOJO;
import org.springframework.stereotype.Repository;

@Repository(value = "UserDAO")
public interface UserDAO {
    UserPOJO userLogin(String username);
    void userRegister(UserPOJO userPOJO);
    UserPOJO countUserName(UserPOJO username);
    String getUserRoles(String username);
}
