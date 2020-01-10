package com.studentdemo.ssystem.DAO;

import com.studentdemo.ssystem.POJO.CourseInfoPOJO;
import org.springframework.stereotype.Repository;

@Repository(value = "CourseDAO")
public interface CourseDAO {
    CourseInfoPOJO selectCourseById(Integer id);
    void updateCourseById(CourseInfoPOJO courseInfoPOJO);
    void delCourseById(Integer id);
    void addCourse(CourseInfoPOJO courseInfoPOJO);
}
