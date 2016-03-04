package dao.Impl;

import dao.RegisterDao;
import dao.StudentDao;
import helper.Closer;
import model.Record;
import model.Register;
import service.impl.ConnectionService;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class RegisterDaoImpl implements RegisterDao {
    private static RegisterDao dao;
    private RegisterDaoImpl(){}
    public static synchronized RegisterDao getInstance(){
        if(dao == null){
            dao = new RegisterDaoImpl();
        }
        return dao;
    }

    private Connection connection;
    private PreparedStatement statement;

    private static final String ADD_STUDENT = "INSERT INTO REGISTER(STUDENT_ID, COURSE_ID) VALUES ( ?, ?)";
    private static final String PUT_MARK = "UPDATE REGISTER SET MARK = ? WHERE STUDENT_ID = ? AND COURSE_ID = ?";
    private static final String GET_REGISTER = "SELECT REGISTER.*, STUDENT.FULLNAME, COURSE.NAME FROM REGISTER, STUDENT, COURSE WHERE COURSE_ID = COURSE.ID AND STUDENT_ID = STUDENT.ID AND COURSE_ID = ?";
    private static final String GET_MARK = "SELECT MARK FROM REGISTER WHERE COURSE_ID = ? AND STUDENT_ID = ?";


    @Override
    public void addStudent(Record record) {
        try {
            connection = ConnectionService.getConnection();
            statement = connection.prepareStatement(ADD_STUDENT);
            statement.setLong(1, record.getStudentId());
            statement.setLong(2, record.getCourseId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Closer.close(connection);
        }
    }

    @Override
    public void putMark(Record record) {
        try {
            connection = ConnectionService.getConnection();
            statement = connection.prepareStatement(PUT_MARK);
            statement.setInt(1, record.getMark());
            statement.setLong(2, record.getStudentId());
            statement.setLong(3, record.getCourseId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Closer.close(connection);
        }
    }

    @Override
    public Register getRegister(Long courseId) {
        Register register = new Register();
        String courseName = null;
        try {
            connection = ConnectionService.getConnection();
            statement = connection.prepareStatement(GET_REGISTER);
            statement.setLong(1, courseId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Record record = new Record();
                record.setStudentId(rs.getLong(1));
                record.setCourseId(rs.getLong(2));
                record.setMark(rs.getInt(3));
                record.setStudentName(rs.getString(4));
                if(courseName == null){
                    courseName = rs.getString(5);
                }
                register.addRecord(record);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Closer.close(connection);
        }
        register.setCourseName(courseName);
        return register;
    }

    @Override
    public Record getMark(Record record) {
        try {
            connection = ConnectionService.getConnection();
            statement = connection.prepareStatement(GET_MARK);
            statement.setLong(1, record.getCourseId());
            statement.setLong(2, record.getStudentId());
            ResultSet rs = statement.executeQuery();
            rs.next();
            record.setMark(rs.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Closer.close(connection);
        }
        return record;
    }
}
