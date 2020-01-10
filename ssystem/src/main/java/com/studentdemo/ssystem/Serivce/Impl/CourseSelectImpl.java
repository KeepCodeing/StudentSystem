package com.studentdemo.ssystem.Serivce.Impl;

import com.studentdemo.ssystem.DAO.CourseSeleDAO;
import com.studentdemo.ssystem.POJO.CourseInfoPOJO;
import com.studentdemo.ssystem.POJO.CourseSeleListPOJO;
import com.studentdemo.ssystem.POJO.CourseSelePOJO;
import com.studentdemo.ssystem.Serivce.ICourseSelect;
import com.studentdemo.ssystem.tools.get_course_list;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "CourseSelectImpl")
public class CourseSelectImpl implements ICourseSelect {

    @Resource(name = "CourseSeleDAO")
    private CourseSeleDAO courseSeleDAO;

    // 获取某个学生的选修课列表
    @Override
    public CourseSelePOJO getStudentSele(Long id) {
        return courseSeleDAO.getStudentSele(id);
    }

    // 获取某个年级的学生的选修课列表
    @Override
    public List<CourseInfoPOJO> getOtherSeleList(Integer grade) {
        return courseSeleDAO.getOtherSeleList(grade);
    }

    // 获取某个年级的学生的必修课列表
    @Override
    public List<CourseInfoPOJO> getMustSeleList(Integer grade) {
        return courseSeleDAO.getMustSeleList(grade);
    }

    // 添加选修课
    @Override
    public void addOtherSele(CourseSeleListPOJO courseSeleListPOJO) {
        CourseSelePOJO studentSele = courseSeleDAO.getStudentSele(courseSeleListPOJO.getId());
        String result = get_course_list.ret_list(studentSele.getCourse_info(), courseSeleListPOJO.getCourse_list());
        studentSele.setCourse_info(result);
        courseSeleDAO.addOtherSele(studentSele);
    }
}
