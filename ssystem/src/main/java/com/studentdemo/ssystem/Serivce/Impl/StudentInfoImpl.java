package com.studentdemo.ssystem.Serivce.Impl;

import com.studentdemo.ssystem.DAO.CourseSeleDAO;
import com.studentdemo.ssystem.DAO.StudentDAO;
import com.studentdemo.ssystem.POJO.CourseInfoPOJO;
import com.studentdemo.ssystem.POJO.CourseSelePOJO;
import com.studentdemo.ssystem.POJO.StudentInfoPOJO;
import com.studentdemo.ssystem.Serivce.IStudentInfo;
import com.studentdemo.ssystem.tools.get_course_list;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service(value = "StudentInfoImpl")
public class StudentInfoImpl implements IStudentInfo {

    @Resource(name = "StudentDAO")
    private StudentDAO studentDAO;

    @Resource(name = "CourseSeleDAO")
    private CourseSeleDAO courseSeleDAO;

    private void initCourseSele(StudentInfoPOJO infoPOJO) {
        List<CourseInfoPOJO> mustSeleList = courseSeleDAO.getMustSeleList(infoPOJO.getGrade());
        // 对选课列表进行格式化
        List<Integer> course_id = new ArrayList<>();
        mustSeleList.forEach((courseInfoPOJO -> {
            course_id.add(courseInfoPOJO.getId());
        }));
        String result = get_course_list.ret_list("", course_id);
        courseSeleDAO.addMustSele(new CourseSelePOJO(infoPOJO.getId(), result));
    }

    @Override
    public StudentInfoPOJO selectStudentInfoById(Long id) {
        return studentDAO.getStudentInfoById(id);
    }

    @Override
    public void addStudentInfo(StudentInfoPOJO infoPOJO) {
        studentDAO.addStudentInfo(infoPOJO);
        // 对学新添加的学生进行必修课初始化
        initCourseSele(infoPOJO);
    }

    @Override
    public void updateStudentInfo(StudentInfoPOJO infoPOJO) {
        studentDAO.updateStudentInfo(infoPOJO);
    }

    @Override
    public void delStudentInfo(Long id) {
        studentDAO.delStudentInfo(id);
        // 删除学生信息的同时删除学生选课记录
        courseSeleDAO.delStudentSeleAll(id);
    }
}
