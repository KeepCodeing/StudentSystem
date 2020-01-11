package com.studentdemo.ssystem.POJO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CourseInfoPOJOEx extends CourseInfoPOJO {
    // 这个实体类主要用于查询学生成绩时存放成绩和科目信息
    private Float score;
}
