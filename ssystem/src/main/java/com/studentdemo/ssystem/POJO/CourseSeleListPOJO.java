package com.studentdemo.ssystem.POJO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "用来存放学生id及其待添加的选修课id的类")
public class CourseSeleListPOJO {
    // 这个类存在的原因是@RequestBody注解只能将JSON数据解析为类
    @ApiModelProperty(value = "学生id")
    private Long id;

    @ApiModelProperty(value = "要添加的选修课的id列表")
    private List<Integer> course_list;
}
