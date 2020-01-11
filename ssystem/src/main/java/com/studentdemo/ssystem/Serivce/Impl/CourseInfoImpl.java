package com.studentdemo.ssystem.Serivce.Impl;

import com.studentdemo.ssystem.DAO.CourseDAO;
import com.studentdemo.ssystem.DAO.CourseSeleDAO;
import com.studentdemo.ssystem.POJO.CourseInfoPOJO;
import com.studentdemo.ssystem.POJO.CourseInfoPOJOEx;
import com.studentdemo.ssystem.POJO.CourseSelePOJO;
import com.studentdemo.ssystem.Serivce.ICourseInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service(value = "CourseInfoImpl")
public class CourseInfoImpl implements ICourseInfo {

    @Resource(name = "CourseDAO")
    private CourseDAO courseDAO;

    @Resource(name = "CourseSeleDAO")
    private CourseSeleDAO courseSeleDAO;

    private CourseInfoPOJOEx ret_CourseInfoPOJOEx(CourseInfoPOJO courseInfoPOJO) {
        // 因为不能向上转型，就有了这个方法
        CourseInfoPOJOEx courseInfoPOJOEx = new CourseInfoPOJOEx();
        courseInfoPOJOEx.setCollege(courseInfoPOJO.getCollege());
        courseInfoPOJOEx.setCourse_name(courseInfoPOJO.getCourse_name());
        courseInfoPOJOEx.setId(courseInfoPOJO.getId());
        courseInfoPOJOEx.setJudge(courseInfoPOJO.getJudge());
        courseInfoPOJOEx.setMust(courseInfoPOJO.getMust());
        courseInfoPOJOEx.setTime(courseInfoPOJO.getTime());
        courseInfoPOJOEx.setStudy_score(courseInfoPOJO.getStudy_score());
        courseInfoPOJOEx.setYear(courseInfoPOJO.getYear());
        return courseInfoPOJOEx;
    }

    // 对学生选课进行格式化并返回学生选课信息和成绩的方法，因为之前写的工具类中
    // 需要访问DAO层的API，但是并没有将这个工具类加入到容器中所以一直在NP，但是
    // 发现问题后尝试将其加入容器却直接报错了，因为static类加载优于容器生成
    private List<CourseInfoPOJOEx> my_parse(CourseSelePOJO courseSelePOJO) {
        String courses = courseSelePOJO.getCourse_info();
        List<CourseInfoPOJOEx> courseInfoPOJOExes = new ArrayList<>();
        String[] split = courses.split("/");
        for (String s : split) {
            // s[0] 课程id，可以通过调用DAO获取课程信息
            // s[1] 学生得分，可以将其封装到POJO里
            String[] id_score = s.split("-");
            if (id_score[0] == null || id_score[0].equals("")) continue;
            if (id_score[1] == null || id_score[1].equals("")) continue;
            float score;
            Integer id = Integer.valueOf(id_score[0]);
            CourseInfoPOJOEx courseInfoPOJOEx = ret_CourseInfoPOJOEx(courseDAO.selectCourseById(id));
            if (id_score[1].equals("*")) {
                score = (float)(-1.0);
            } else {
                score = Float.parseFloat(id_score[1]);
            }
            courseInfoPOJOEx.setScore(score);
            courseInfoPOJOExes.add(courseInfoPOJOEx);
        }
        return courseInfoPOJOExes;
    }

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

    @Override
    public List<CourseInfoPOJOEx> getStudentScore(Long id) {
        // 首先获取学生的选课列表
        CourseSelePOJO studentSele = courseSeleDAO.getStudentSele(id);
        // 然后解析其选课列表，再对POJO进行封装
        return my_parse(studentSele);
    }
}
