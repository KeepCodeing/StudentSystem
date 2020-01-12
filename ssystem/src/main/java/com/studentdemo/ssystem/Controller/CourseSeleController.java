package com.studentdemo.ssystem.Controller;

import com.studentdemo.ssystem.POJO.CourseIdListPOJO;
import com.studentdemo.ssystem.POJO.CourseInfoPOJO;
import com.studentdemo.ssystem.POJO.CourseSeleListPOJO;
import com.studentdemo.ssystem.POJO.CourseSelePOJO;
import com.studentdemo.ssystem.Serivce.ICourseSelect;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "选课的API")
@RestController
@RequestMapping("/api")
public class CourseSeleController {
    // 因为必修课在新建学生时就已经默认存入了，所以在这里
    // 特意对选修课进行封装
    @Resource(name = "CourseSelectImpl")
    private ICourseSelect iCourseSelect;

    @ApiOperation(value = "添加选修课", notes = "添加选修课前需要把之前选的课先取到")
    @PostMapping("/addOtherCourse")
    public void addOtherCourse(@RequestBody CourseSeleListPOJO courseSeleListPOJO) {
        iCourseSelect.addOtherSele(courseSeleListPOJO);
    }

    @ApiOperation(value = "获取某个学生的选课列表")
    @PostMapping("/getStudentSele/{id}")
    public CourseSelePOJO getStudentSele(@PathVariable Long id) {
        return iCourseSelect.getStudentSele(id);
    }

    @ApiOperation(value = "获取某个年级的学生的选修课列表")
    @PostMapping("/getOtherSeleList/{grade}")
    public List<CourseInfoPOJO> getOtherSeleList(@PathVariable Integer grade) {
        return iCourseSelect.getOtherSeleList(grade);
    }

    @ApiOperation(value = "获取某个年级的学生的必修课列表")
    @PostMapping("/getMustSeleList/{grade}")
    public List<CourseInfoPOJO> getMustSeleList(@PathVariable Integer grade) {
        return iCourseSelect.getMustSeleList(grade);
    }

    @ApiOperation(value = "删除学生选课")
    @PostMapping("/delCourses")
    public void delCourses(@RequestBody CourseIdListPOJO courseIdList) {
        iCourseSelect.updateSelection(courseIdList);
    }
}
