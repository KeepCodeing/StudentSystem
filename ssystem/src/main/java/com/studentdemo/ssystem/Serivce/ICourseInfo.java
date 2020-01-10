package com.studentdemo.ssystem.Serivce;

import com.studentdemo.ssystem.POJO.CourseInfoPOJO;

// 主要对课程信息进行增删改查的接口
public interface ICourseInfo {
    CourseInfoPOJO selectCourseById(Integer id);
    void updateCourseById(CourseInfoPOJO courseInfoPOJO);
    void delCourseById(Integer id);
    void addCourse(CourseInfoPOJO courseInfoPOJO);
}
