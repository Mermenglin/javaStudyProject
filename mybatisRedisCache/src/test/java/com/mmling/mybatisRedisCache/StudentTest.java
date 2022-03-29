package com.mmling.mybatisRedisCache;

import com.mmling.mybatisRedisCache.dao.StudentDao;
import com.mmling.mybatisRedisCache.entity.Student;
import javafx.scene.Scene;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author meimengling
 * @version 1.0
 * @date 2021-5-26 15:59
 */
@SpringBootTest
public class StudentTest {

    @Resource
    private StudentDao studentDao;

    @Test
    void contextLoads() {
        List<Student> students = this.studentDao.queryAllByLimit(10000, 10001);
        Map<Integer, Student> collect = students.stream().collect(Collectors.toMap(Student::getId, s -> s));

        System.out.println(students);
        System.out.println(collect);
    }
}
