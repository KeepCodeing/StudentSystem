package com.studentdemo.ssystem.POJO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "课程信息实体类")
public class CourseInfoPOJO {

    @ApiModelProperty("课程id")
    private Integer id;

    @ApiModelProperty("课程名")
    private String course_name;

    @ApiModelProperty("必修否")
    private Integer must;

    @ApiModelProperty("学时")
    private String time;

    @ApiModelProperty("评分标准")
    private String judge;

    @ApiModelProperty("学年")
    private Integer year;

    @ApiModelProperty("得分")
    private Float score;

    @ApiModelProperty("开课学院")
    private String college;

}
