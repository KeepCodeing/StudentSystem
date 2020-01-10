package com.studentdemo.ssystem.POJO;

import lombok.Data;

import java.util.List;

@Data
public class CourseSeleListPOJO {
    // 这个类存在的原因是@RequestBody注解只能将JSON数据解析为类
    private Long id;
    private List<Integer> course_list;
}
