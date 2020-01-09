package com.studentdemo.ssystem.DAO;

import com.studentdemo.ssystem.POJO.StudentInfoPOJO;
import org.springframework.stereotype.Repository;

@Repository(value = "StudentDAO")
public interface StudentDAO {
    StudentInfoPOJO getStudentInfoById(Long id);
    void addStudentInfo(StudentInfoPOJO infoPOJO);
    void updateStudentInfo(StudentInfoPOJO infoPOJO);
    void delStudentInfo(Long id);
}
