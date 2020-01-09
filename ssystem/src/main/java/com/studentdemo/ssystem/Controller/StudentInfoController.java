package com.studentdemo.ssystem.Controller;

import com.studentdemo.ssystem.POJO.StudentInfoPOJO;
import com.studentdemo.ssystem.Serivce.IStudentInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping("/api")
@RestController
@Api(tags = "操作学生信息的API")
public class StudentInfoController {

    @Resource(name = "StudentInfoImpl")
    private IStudentInfo studentInfo;

    @GetMapping("/getStudentInfo/{id}")
    @ApiOperation(value = "通过学生id获取学生信息", notes = "通过id查询数据")
    public StudentInfoPOJO getStudentInfoById( @PathVariable Long id) {
        return studentInfo.selectStudentInfoById(id);
    }

    @PostMapping("/addStudentInfo")
    @ApiOperation(value = "添加学生信息", notes = "通过请求体里的JSON数据增加学生信息")
    public void addStudentInfo(@RequestBody StudentInfoPOJO info) {
        studentInfo.addStudentInfo(info);
    }

    @PostMapping("/updateStudentInfo")
    @ApiOperation(value = "更新学生信息（通过id）", notes = "使用动态SQL防止数据写入错误")
    public void updateStudentInfo(@RequestBody StudentInfoPOJO infoPOJO) {
        studentInfo.updateStudentInfo(infoPOJO);
    }

    @GetMapping("/delStudentInfo/{id}")
    @ApiOperation(value = "通过学生id删除学生信息", notes = "通过id删除信息")
    public void delStudentInfo(@PathVariable Long id) {
        studentInfo.delStudentInfo(id);
    }
}
