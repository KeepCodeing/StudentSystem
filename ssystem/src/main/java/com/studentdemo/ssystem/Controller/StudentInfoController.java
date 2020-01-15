package com.studentdemo.ssystem.Controller;

import com.studentdemo.ssystem.POJO.StudentInfoPOJO;
import com.studentdemo.ssystem.Serivce.IStudentInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping("/api")
@RestController
@Api(tags = "操作学生信息的API")
public class StudentInfoController {

    @Resource(name = "StudentInfoImpl")
    private IStudentInfo studentInfo;

    @GetMapping("/getStudentInfo/{id}")
    @ApiOperation(value = "通过学生ID获取学生信息")
    @ApiImplicitParam(paramType = "path", dataType = "Long", name = "id", value = "学生ID", required = true, example = "1")
    public StudentInfoPOJO getStudentInfoById( @PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // System.out.println(authentication);
        if (authentication != null) {
            System.out.println(authentication.getPrincipal());
        }
        return studentInfo.selectStudentInfoById(id);
    }

    @PostMapping("/addStudentInfo")
    @ApiOperation(value = "添加学生信息", notes = "通过请求体里的JSON数据增加学生信息")
    // @ApiImplicitParam(paramType = "StudentInfoPOJO", dataType = "StudentInfoPOJO", name = "info", value = "学生信息", required = true)
    public void addStudentInfo(@RequestBody StudentInfoPOJO info) {
        studentInfo.addStudentInfo(info);
    }

    @PostMapping("/updateStudentInfo")
    @ApiOperation(value = "更新学生信息（通过id）", notes = "使用动态SQL防止数据写入错误")
    // @ApiImplicitParam(paramType = "StudentInfoPOJO", dataType = "StudentInfoPOJO", name = "infoPOJO", value = "学生信息", required = true)
    public void updateStudentInfo(@RequestBody StudentInfoPOJO infoPOJO) {
        studentInfo.updateStudentInfo(infoPOJO);
    }

    @GetMapping("/delStudentInfo/{id}")
    @ApiImplicitParam(paramType = "path", dataType = "Long", name = "id", value = "学生ID", required = true, example = "1")
    @ApiOperation(value = "通过学生id删除学生信息", notes = "通过id删除信息")
    public void delStudentInfo(@PathVariable Long id) {
        studentInfo.delStudentInfo(id);
    }

}
