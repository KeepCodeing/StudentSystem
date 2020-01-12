package com.studentdemo.ssystem.Serivce.Impl;

import com.studentdemo.ssystem.DAO.CourseSeleDAO;
import com.studentdemo.ssystem.POJO.CourseIdListPOJO;
import com.studentdemo.ssystem.POJO.CourseInfoPOJO;
import com.studentdemo.ssystem.POJO.CourseSeleListPOJO;
import com.studentdemo.ssystem.POJO.CourseSelePOJO;
import com.studentdemo.ssystem.Serivce.ICourseSelect;
import com.studentdemo.ssystem.tools.get_course_list;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service(value = "CourseSelectImpl")
public class CourseSelectImpl implements ICourseSelect {

    @Resource(name = "CourseSeleDAO")
    private CourseSeleDAO courseSeleDAO;

    private CourseSelePOJO format_selection(CourseIdListPOJO courseIdList) {
        // 对学生选课进行格式化，通过选课id来删除选课
        // selection列表就是id列表
        // courseIdList 中包含了学生id及其要删除的课程的id
        // 首先获取到这个学生的选课列表
        String result = "";
        CourseSelePOJO studentSele = courseSeleDAO.getStudentSele(courseIdList.getId());
        // 然后再对其原选课列表进行操作
        String[] seleCourseInfoSplited = studentSele.getCourse_info().split("/");
        for (Integer id : courseIdList.getCourse_id_list()) {
            for (int i = 0; i < seleCourseInfoSplited.length; i++) {
                // 将包含要剔除的学科id的字符串换为空
                if (seleCourseInfoSplited[i].contains(String.valueOf(id))) {
                    seleCourseInfoSplited[i] = "";
                }
            }
        }
        for (String s : seleCourseInfoSplited) {
            if (!s.equals("")) result += s + "/";
        }

        return new CourseSelePOJO(courseIdList.getId(), result);
    }

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

    @Override
    public void updateSelection(CourseIdListPOJO courseIdList) {
        // 写的屑方法，效率堪忧，而且没有用到任何jdk8及以上提供的便捷方法
        courseSeleDAO.updateSelection(format_selection(courseIdList));
    }
}
