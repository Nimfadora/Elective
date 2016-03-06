package dao;

import model.Course;
import model.SortFilterParams;

import java.util.List;

public interface CourseDao {
    void create(Course course);
    Course read(Long id);
    void update(Course course);
    void delete(Long id);
    List<Course> getAll();
    List<Course> getUnassigned();
    List<Course> getStartedCourses();
    void setTutor(Long courseId, Long tutorId);
    List<Course> getCoursesByTutor(Long tutorId);
    List<Course> getCoursesByStudent(Long studentId);
    List<Course> getFilteredSortedCourses(SortFilterParams params);
}
