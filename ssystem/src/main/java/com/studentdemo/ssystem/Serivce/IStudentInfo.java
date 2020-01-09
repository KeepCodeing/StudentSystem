package com.studentdemo.ssystem.Serivce;

import com.studentdemo.ssystem.POJO.StudentInfoPOJO;


public interface IStudentInfo {
    StudentInfoPOJO selectStudentInfoById(Long id);
    void addStudentInfo(StudentInfoPOJO infoPOJO);
    void updateStudentInfo(StudentInfoPOJO infoPOJO);
    void delStudentInfo(Long id);
}
