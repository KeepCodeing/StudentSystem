package com.studentdemo.ssystem.Serivce.Impl;

import com.studentdemo.ssystem.DAO.StudentDAO;
import com.studentdemo.ssystem.POJO.StudentInfoPOJO;
import com.studentdemo.ssystem.Serivce.IStudentInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "StudentInfoImpl")
public class StudentInfoImpl implements IStudentInfo {

    @Resource(name = "StudentDAO")
    private StudentDAO studentDAO;

    @Override
    public StudentInfoPOJO selectStudentInfoById(Long id) {
        return studentDAO.getStudentInfoById(id);
    }

    @Override
    public void addStudentInfo(StudentInfoPOJO infoPOJO) {
        studentDAO.addStudentInfo(infoPOJO);
    }

    @Override
    public void updateStudentInfo(StudentInfoPOJO infoPOJO) {
        studentDAO.updateStudentInfo(infoPOJO);
    }

    @Override
    public void delStudentInfo(Long id) {
        studentDAO.delStudentInfo(id);
    }
}
