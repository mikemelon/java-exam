package cn.lynu.lyq.java_exam.dao;

import cn.lynu.lyq.java_exam.entity.Course;

import java.util.List;

public interface CourseDao {
    Course findById(int id);
    List<Course> findAll();
    int countAllCourse();
    List<Course> findByName(String name);
    List<Course> findAllWithPage(int pageIndex, int pageSize);
    void save(Course c);
    void update(Course c);
    void delete(Course c);
}
