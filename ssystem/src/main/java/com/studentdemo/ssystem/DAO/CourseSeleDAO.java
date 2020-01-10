package com.studentdemo.ssystem.DAO;

import com.studentdemo.ssystem.POJO.CourseInfoPOJO;
import com.studentdemo.ssystem.POJO.CourseSelePOJO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "CourseSeleDAO")
public interface CourseSeleDAO {
    // 获取某个学生的选课列表
    CourseSelePOJO getStudentSele(Long id);

    // 通过学生id向数据库中添加学生必修课的初始形式，主要作用于添加学生信息时的初始化
    void addMustSele(CourseSelePOJO courseSelePOJO);

    // 通过学生年级获取必修课列表，主要作用于添加学生信息时的初始化
    List<CourseInfoPOJO> getMustSeleList(Integer grade);

    // 删除学生全部选课记录，主要作用于删除学生信息时同步删除选课信息
    void delStudentSeleAll(Long id);

    // 通过学生年级获取非必修课列表
    List<CourseInfoPOJO> getOtherSeleList(Integer grade);

    // 添加非必修课
    void addOtherSele(CourseSelePOJO courseSelePOJO);

}
