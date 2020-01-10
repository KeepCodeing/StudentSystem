package com.studentdemo.ssystem.Serivce.Impl;

import com.studentdemo.ssystem.DAO.CourseDAO;
import com.studentdemo.ssystem.POJO.CourseInfoPOJO;
import com.studentdemo.ssystem.Serivce.ICourseInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "CourseInfoImpl")
public class CourseInfoImpl implements ICourseInfo {

    @Resource(name = "CourseDAO")
    private CourseDAO courseDAO;

    @Override
    public CourseInfoPOJO selectCourseById(Integer id) {
        return courseDAO.selectCourseById(id);
    }

    @Override
    public void updateCourseById(CourseInfoPOJO courseInfoPOJO) {
        courseDAO.updateCourseById(courseInfoPOJO);
    }

    @Override
    public void delCourseById(Integer id) {
        courseDAO.delCourseById(id);
    }

    @Override
    public void addCourse(CourseInfoPOJO courseInfoPOJO) {
        courseDAO.addCourse(courseInfoPOJO);
    }
}
