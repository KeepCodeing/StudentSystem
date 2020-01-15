package com.studentdemo.ssystem.POJO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "用来存放用户角色的类")
@Data
public class UserRolePOJO {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户角色")
    private String roles;

    public UserRolePOJO() {
    }

    public UserRolePOJO(String username, String roles) {
        this.username = username;
        this.roles = roles;
    }
}
