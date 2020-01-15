package com.studentdemo.ssystem.DAO;

import com.studentdemo.ssystem.Exceptions.RoleException;
import com.studentdemo.ssystem.POJO.UserPOJO;
import com.studentdemo.ssystem.POJO.UserRolePOJO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "UserDAO")
public interface UserDAO {
    UserPOJO userLogin(String username);
    void userRegister(UserPOJO userPOJO);
    UserPOJO countUserName(UserPOJO username);
    String getUserRoles(String username);
    void initUserRole(UserRolePOJO rolePOJO);
    void setUserRole(UserRolePOJO rolePOJO) throws RoleException;

}
