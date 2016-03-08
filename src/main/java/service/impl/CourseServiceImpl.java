package service.impl;

import dao.CourseDao;
import dao.Impl.CourseDaoImpl;
import model.Course;
import service.CourseService;

import java.util.List;

public class CourseServiceImpl implements CourseService {
    private static CourseDao dao = CourseDaoImpl.getInstance();
    private static CourseService service;
    private CourseServiceImpl(){}
    public static synchronized CourseService getInstance(){
        if(service == null){
            service = new CourseServiceImpl();
        }
        return service;
    }

    @Override
    public void create(Course course) {
        dao.create(course);
    }

    @Override
    public Course read(Long id) {
        return dao.read(id);
    }

    @Override
    public void update(Course course) {
        dao.update(course);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public List<Course> getAll() {
        return dao.getAll();
    }

    @Override
    public List<Course> getUnassigned() {
        return dao.getUnassigned();
    }

    @Override
    public List<Course> getStartedCourses() {
        return dao.getStartedCourses();
    }

    @Override
    public void setTutor(Long courseId, Long tutorId) {
        dao.setTutor(courseId, tutorId);
    }

    @Override
    public List<Course> getCoursesByTutor(Long tutorId) {
        return dao.getCoursesByTutor(tutorId);
    }

    @Override
    public List<Course> getCoursesByStudent(Long studentId) {
        return dao.getCoursesByStudent(studentId);
    }

    @Override
    public Boolean checkCourseToTutor(Long courseId, Long tutorId) {
        return dao.checkCourseToTutor(courseId, tutorId);
    }
}
