package dao.Impl;

import dao.CourseDao;
import helper.Closer;
import model.Course;
import service.impl.ConnectionService;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {
    private static CourseDao dao;
    private CourseDaoImpl(){}
    public static synchronized CourseDao getInstance(){
        if(dao == null){
            dao = new CourseDaoImpl();
        }
        return dao;
    }

    private Connection connection;
    private PreparedStatement statement;

    private static final String CREATE = "INSERT INTO COURSE(NAME, DURATION, TOPIC_ID, TUTOR_ID, STATUS)  VALUES ( ?, ?, ?, ?, ?)";
    private static final String READ = "SELECT COURSE.*, TOPIC.TITLE, TUTOR.FULLNAME  FROM COURSE, TUTOR, TOPIC WHERE TUTOR_ID = TUTOR.ID AND TOPIC_ID = TOPIC.ID AND COURSE.ID = ?";
    private static final String UPDATE = "UPDATE COURSE SET NAME = ?, DURATION = ?, TOPIC_ID = ?, TUTOR_ID = ?, STATUS = ? WHERE ID = ?";
    private static final String SET_TUTOR = "UPDATE COURSE SET TUTOR_ID = ? WHERE ID = ?";
    private static final String DELETE = "DELETE FROM COURSE WHERE ID = ?";
    private static final String GET_ALL = "SELECT COURSE.*, TOPIC.TITLE, TUTOR.FULLNAME  FROM COURSE LEFT JOIN TUTOR ON (TUTOR_ID = TUTOR.ID) INNER JOIN TOPIC ON(TOPIC_ID = TOPIC.ID)";
    private static final String GET_UNASSIGNED = "SELECT ID, NAME FROM COURSE WHERE TUTOR_ID IS NULL";
    private static final String GET_STARTED = "SELECT COURSE.*, TOPIC.TITLE, TUTOR.FULLNAME, COUNT(REGISTER.STUDENT_ID) FROM COURSE, TUTOR, TOPIC, REGISTER WHERE TUTOR_ID = TUTOR.ID AND TOPIC_ID = TOPIC.ID AND STATUS = 'started' AND REGISTER.COURSE_ID = COURSE.ID";
    private static final String GET_COURSES_BY_TUTOR = "SELECT COURSE.ID, COURSE.NAME, COURSE.STATUS, COUNT(REGISTER.STUDENT_ID) FROM COURSE, TUTOR, REGISTER WHERE COURSE.TUTOR_ID = TUTOR.ID AND COURSE.ID = REGISTER.COURSE_ID AND COURSE.TUTOR_ID = ? GROUP BY REGISTER.COURSE_ID, COURSE.ID, COURSE.NAME, COURSE.STATUS";

    @Override
    public void create(Course course) {
        try {
            connection = ConnectionService.getConnection();
            statement = connection.prepareStatement(CREATE);
            statement.setString(1, course.getName());
            statement.setInt(2, course.getDuration());
            statement.setLong(3, course.getTopicId());

            try {
                statement.setLong(4, course.getTutorId());
            }catch (NullPointerException e){
                statement.setNull(4, Types.INTEGER);
            }
            statement.setString(5, course.getStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Closer.close(connection);
        }
    }

    @Override
    public Course read(Long id) {
        Course course = new Course();
        try {
            connection = ConnectionService.getConnection();
            statement = connection.prepareStatement(READ);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            course.setId(rs.getLong(1));
            course.setTopicId(rs.getLong(2));
            course.setTutorId(rs.getLong(3));
            course.setStatus(rs.getString(4));
            course.setDuration(rs.getInt(5));
            course.setName(rs.getString(6));
            course.setTopic(rs.getString(7));
            course.setTutor(rs.getString(8));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Closer.close(connection);
        }
        return course;
    }

    @Override
    public void update(Course course) {
        try {
            connection = ConnectionService.getConnection();
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, course.getName());
            statement.setInt(2, course.getDuration());
            statement.setLong(3, course.getTopicId());
            statement.setLong(4, course.getTutorId());
            statement.setString(5, course.getStatus());
            statement.setLong(6, course.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Closer.close(connection);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            connection = ConnectionService.getConnection();
            statement = connection.prepareStatement(DELETE);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Closer.close(connection);
        }
    }

    @Override
    public List<Course> getAll() {
        List<Course> courses = new LinkedList<>();
        try {
            connection = ConnectionService.getConnection();
            statement = connection.prepareStatement(GET_ALL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Course course = new Course();
                course.setId(rs.getLong(1));
                course.setTopicId(rs.getLong(2));
                course.setTutorId(rs.getLong(3));
                course.setStatus(rs.getString(4));
                course.setDuration(rs.getInt(5));
                course.setName(rs.getString(6));
                course.setTopic(rs.getString(7));
                course.setTutor(rs.getString(8));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Closer.close(connection);
        }
        return courses;
    }

    @Override
    public List<Course> getUnassigned() {
        List<Course> courses = new LinkedList<>();
        try {
            connection = ConnectionService.getConnection();
            statement = connection.prepareStatement(GET_UNASSIGNED);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Course course = new Course();
                course.setId(rs.getLong(1));
                course.setName(rs.getString(2));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Closer.close(connection);
        }
        return courses;
    }

    @Override
    public List<Course> getStartedCourses() {
        List<Course> courses = new LinkedList<>();
        try {
            connection = ConnectionService.getConnection();
            statement = connection.prepareStatement(GET_STARTED);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Course course = new Course();
                course.setId(rs.getLong(1));
                course.setTopicId(rs.getLong(2));
                course.setTutorId(rs.getLong(3));
                course.setStatus(rs.getString(4));
                course.setDuration(rs.getInt(5));
                course.setName(rs.getString(6));
                course.setTopic(rs.getString(7));
                course.setTutor(rs.getString(8));
                course.setNumOfStudents(rs.getInt(9));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Closer.close(connection);
        }
        return courses;
    }

    @Override
    public void setTutor(Long courseId, Long tutorId) {
        try {
            connection = ConnectionService.getConnection();
            statement = connection.prepareStatement(SET_TUTOR);
            statement.setLong(1, tutorId);
            statement.setLong(2, courseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Closer.close(connection);
        }
    }

    @Override
    public List<Course> getCoursesByTutor(Long tutorId) {
        List<Course> courses = new LinkedList<>();
        try {
            connection = ConnectionService.getConnection();
            statement = connection.prepareStatement(GET_COURSES_BY_TUTOR);
            statement.setLong(1, tutorId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Course course = new Course();
                course.setId(rs.getLong(1));
                course.setName(rs.getString(2));
                course.setStatus(rs.getString(3));
                course.setNumOfStudents(rs.getInt(4));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Closer.close(connection);
        }
        return courses;
    }
}
