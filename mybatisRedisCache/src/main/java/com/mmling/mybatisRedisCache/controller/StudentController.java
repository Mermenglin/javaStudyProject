package com.mmling.mybatisRedisCache.controller;

import com.mmling.mybatisRedisCache.entity.Student;
import com.mmling.mybatisRedisCache.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Student)表控制层
 *
 * @author meiml
 * @since 2019-12-31 11:32:48
 */
@RestController
@RequestMapping("student")
public class StudentController {
    /**
     * 服务对象
     */
    @Resource
    private StudentService studentService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Student selectOne(Integer id) {
        return this.studentService.queryById(id);
    }

    @PostMapping("update")
    public int updateStudent(@RequestBody Student student) {
        return studentService.update(student).getId();
    }

    @PostMapping("add")
    public int insertStudent(@RequestBody Student student) {
        return studentService.insert(student).getId();
    }

    @DeleteMapping("delete")
    public Boolean deleteStudent(Integer id) {
        return studentService.deleteById(id);
    }
}