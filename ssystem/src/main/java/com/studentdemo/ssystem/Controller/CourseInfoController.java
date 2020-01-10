package com.studentdemo.ssystem.Controller;

import com.studentdemo.ssystem.POJO.CourseInfoPOJO;
import com.studentdemo.ssystem.Serivce.ICourseInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
@Api(tags = "操作课程信息的API")
public class CourseInfoController {

    @Resource(name = "CourseInfoImpl")
    private ICourseInfo courseInfo;

    @GetMapping("/getCourseInfoById/{id}")
    @ApiImplicitParam(paramType = "path", dataType = "Integer", name = "id", value = "课程ID", required = true, example = "1")
    @ApiOperation(value = "通过课程id获取课程信息", notes = "通过id查询数据")
    public CourseInfoPOJO getCourseInfoById(@PathVariable Integer id) {
        return courseInfo.selectCourseById(id);
    }

    @ApiOperation(value = "通过课程id删除课程信息", notes = "通过id删除数据")
    @ApiImplicitParam(paramType = "path", dataType = "Integer", name = "id", value = "课程ID", required = true, example = "1")
    @GetMapping("/delCourseInfoById/{id}")
    public void delCourseInfoById(@PathVariable Integer id) {
        courseInfo.delCourseById(id);
    }

    @ApiOperation(value = "添加课程信息", notes = "id自增，无需指定")
    @ApiImplicitParam(paramType = "RequestBody", dataType = "CourseInfoPOJO", name = "courseInfoPOJO", value = "课程信息", required = true)
    @PostMapping("/addCourseInfo")
    public void addCourseInfo(@RequestBody CourseInfoPOJO courseInfoPOJO) {
        courseInfo.addCourse(courseInfoPOJO);
    }

    @ApiOperation(value = "通过课程id更新课程信息", notes = "通过id更新数据")
    @ApiImplicitParam(paramType = "RequestBody", dataType = "CourseInfoPOJO", name = "courseInfoPOJO", value = "课程信息", required = true)
    @PostMapping("/updateCourseInfo")
    public void updateCourseInfo(@RequestBody CourseInfoPOJO courseInfoPOJO) {
        courseInfo.updateCourseById(courseInfoPOJO);
    }
}
