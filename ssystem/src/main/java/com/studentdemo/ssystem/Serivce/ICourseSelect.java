package com.studentdemo.ssystem.Serivce;

import com.studentdemo.ssystem.POJO.CourseInfoPOJO;
import com.studentdemo.ssystem.POJO.CourseSeleListPOJO;
import com.studentdemo.ssystem.POJO.CourseSelePOJO;

import java.util.List;

// 主要对学生选课信息进行增删改查的类
public interface ICourseSelect {
    // 获取某个学生的选课列表
    CourseSelePOJO getStudentSele(Long id);

    // 通过学生年级获取选修课列表
    List<CourseInfoPOJO> getOtherSeleList(Integer grade);

    // 通过学生年级获取必修课列表
    List<CourseInfoPOJO> getMustSeleList(Integer grade);

    // 添加选修课
    void addOtherSele(CourseSeleListPOJO courseSeleListPOJO);
}
