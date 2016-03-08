package dao.Impl;

import dao.StudentDao;
import electiveException.EmailAlreadyExistsException;
import electiveException.UserNotFoundException;
import helper.Closer;
import model.Student;
import model.User;
import service.impl.ConnectionService;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    private static StudentDao dao;
    private StudentDaoImpl(){}
    public static synchronized StudentDao getInstance(){
        if(dao == null){
            dao = new StudentDaoImpl();
        }
        return dao;
    }

    private Connection connection;
    private PreparedStatement statement;

    private static final String CREATE = "INSERT INTO STUDENT(FULLNAME, EMAIL, PASSWORD, AGE)  VALUES ( ?, ?, ?, ?)";
    private static final String READ = "SELECT * FROM STUDENT WHERE ID = ?";
    private static final String GET_ALL = "SELECT * FROM STUDENT ORDER BY FULLNAME ASC";
    private static final String UPDATE = "UPDATE STUDENT SET FULLNAME = ?, AGE = ?, EMAIL = ?, PASSWORD = ? WHERE ID = ?";
    private static final String AUTHORISE = "SELECT * FROM STUDENT WHERE EMAIL = ? AND PASSWORD = ?";
    private static final String CHANGE_BAN = "UPDATE STUDENT SET BAN = NOT BAN WHERE ID = ?";
    private static final String GET_BAN = "SELECT STUDENT.BAN FROM STUDENT WHERE ID = ?";


    @Override
    public Long create(Student student) {
        Long id = null;
        try {
            connection = ConnectionService.getConnection();
            statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getPassword());
            statement.setInt(4, student.getAge());
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            id = rs.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EmailAlreadyExistsException();
        }finally {
            Closer.close(connection);
        }
        return id;
    }


    @Override
    public Student read(Long id) {
        Student student = new Student();
        try {
            connection = ConnectionService.getConnection();
            statement = connection.prepareStatement(READ);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            student.setId(rs.getLong(1));
            student.setEmail(rs.getString(2));
            student.setPassword(rs.getString(3));
            student.setAge(rs.getInt(4));
            student.setBan(rs.getBoolean(5));
            student.setName(rs.getString(6));

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Closer.close(connection);
        }
        return student;
    }

    @Override
    public void update(Student student){
        try {
            connection = ConnectionService.getConnection();
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getPassword());
            statement.setLong(5, student.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Closer.close(connection);
        }
    }

    @Override
    public User authorise(String email, String password) {
        Student student = null;
        try {
            connection = ConnectionService.getConnection();
            statement = connection.prepareStatement(AUTHORISE);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                student = new Student();
                student.setId(rs.getLong(1));
                student.setRole("student");
            }else{
                throw new UserNotFoundException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Closer.close(connection);
        }
        return student;
    }

    @Override
    public List<Student> getAll() {
        List<Student> students = new LinkedList<>();
        try {
            connection = ConnectionService.getConnection();
            statement = connection.prepareStatement(GET_ALL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getLong(1));
                student.setEmail(rs.getString(2));
                student.setPassword(rs.getString(3));
                student.setAge(rs.getInt(4));
                student.setBan(rs.getBoolean(5));
                student.setName(rs.getString(6));
                students.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Closer.close(connection);
        }
        return students;
    }

    @Override
    public void changeBanStatus(Long id) {
        try {
            connection = ConnectionService.getConnection();
            statement = connection.prepareStatement(CHANGE_BAN);
            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Closer.close(connection);
        }
    }

    @Override
    public Boolean getBanStatus(Long id) {
        Boolean ban = null;
        try {
            connection = ConnectionService.getConnection();
            statement = connection.prepareStatement(GET_BAN);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            ban = rs.getBoolean(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Closer.close(connection);
        }
        return ban;
    }

}
