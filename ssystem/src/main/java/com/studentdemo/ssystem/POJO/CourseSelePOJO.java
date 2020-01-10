package com.studentdemo.ssystem.POJO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "学生选课实体类")
public class CourseSelePOJO {
    public CourseSelePOJO(Long id, String course_info) {
        this.id = id;
        this.course_info = course_info;
    }

    @ApiModelProperty("学生id")
    private Long id;

    @ApiModelProperty("学生选课形式")
    private String course_info;
}
