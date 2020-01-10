package com.studentdemo.ssystem.POJO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@ApiModel(description = "学生信息实体类")
public class StudentInfoPOJO {
    @ApiModelProperty("学生ID")
    private Long id;

    @ApiModelProperty("学生姓名")
    private String name;

    @ApiModelProperty("学生年龄")
    private Integer age;

    @ApiModelProperty("学生性别（0为女，1为男）")
    private Integer gender;

    @ApiModelProperty("学生家庭住址")
    private String address;

    @ApiModelProperty("学生手机号码")
    private String phone;

    @ApiModelProperty("学生QQ号码")
    private String qq;
    
    @ApiModelProperty("学生年级")
    private Integer grade;

    @ApiModelProperty("学生班级")
    private String classname;
}
