package com.studentdemo.ssystem.POJO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "用来存放学生id及其要删除的课程的id的类")
public class CourseIdListPOJO {

    @ApiModelProperty(value = "学生id")
    Long id;

    @ApiModelProperty(value = "待删除课程列表")
    List<Integer> course_id_list;
}
