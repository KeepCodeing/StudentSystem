package com.studentdemo.ssystem.DAO;

import com.studentdemo.ssystem.POJO.CourseInfoPOJO;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "CourseDAO")
public interface CourseDAO {
    CourseInfoPOJO selectCourseById(Integer id);
    void updateCourseById(CourseInfoPOJO courseInfoPOJO);
    void delCourseById(Integer id);
    void addCourse(CourseInfoPOJO courseInfoPOJO);
}
