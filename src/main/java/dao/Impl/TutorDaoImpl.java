package dao.Impl;

import dao.TutorDao;
import helper.Closer;
import model.Tutor;
import service.impl.ConnectionService;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class TutorDaoImpl implements TutorDao {
    private static TutorDao dao;
    private TutorDaoImpl(){}
    public static synchronized TutorDao getInstance(){
        if(dao == null){
            dao = new TutorDaoImpl();
        }
        return dao;
    }

    private Connection connection;
    private PreparedStatement statement;

    private static final String CREATE = "INSERT INTO TUTOR(FULLNAME, EMAIL, PASSWORD)  VALUES ( ?, ?, ?)";
    private static final String READ = "SELECT * FROM TUTOR WHERE ID = ?";
    private static final String UPDATE = "UPDATE TUTOR SET FULLNAME = ?, EMAIL = ?, PASSWORD = ? WHERE ID = ?";
    private static final String DELETE = "DELETE FROM TUTOR WHERE ID = ?";
    private static final String AUTHORISE = "SELECT ID FROM TUTOR WHERE EMAIL = ? AND PASSWORD = ?";
    private static final String GET_ALL = "SELECT * FROM TUTOR";

    @Override
    public Long create(Tutor tutor) {
        Long id = null;
        try {
            connection = ConnectionService.getConnection();
            statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, tutor.getName());
            statement.setString(2, tutor.getEmail());
            statement.setString(3, tutor.getPassword());
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            id = rs.getLong(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Closer.close(connection);
        }
        return id;
    }


    @Override
    public Tutor read(Long id) {
        Tutor tutor = new Tutor();
        try {
            connection = ConnectionService.getConnection();
            statement = connection.prepareStatement(READ);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            tutor.setId(rs.getLong(1));
            tutor.setEmail(rs.getString(2));
            tutor.setPassword(rs.getString(3));
            tutor.setName(rs.getString(4));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Closer.close(connection);
        }
        return tutor;
    }

    @Override
    public void update(Tutor tutor){
        try {
            connection = ConnectionService.getConnection();
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, tutor.getName());
            statement.setString(2, tutor.getEmail());
            statement.setString(3, tutor.getPassword());
            statement.setLong(4, tutor.getId());
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
    public Tutor authorise(String email, String password) {
         Tutor tutor = null;
        try {
            connection = ConnectionService.getConnection();
            statement = connection.prepareStatement(AUTHORISE);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                tutor = new Tutor();
                tutor.setId(rs.getLong(1));
                tutor.setRole("tutor");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Closer.close(connection);
        }
        return tutor;
    }

    @Override
    public List<Tutor> getAll() {
        List<Tutor> tutors = new LinkedList<>();
        try {
            connection = ConnectionService.getConnection();
            statement = connection.prepareStatement(GET_ALL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Tutor tutor = new Tutor();
                tutor.setId(rs.getLong(1));
                tutor.setEmail(rs.getString(2));
                tutor.setPassword(rs.getString(3));
                tutor.setName(rs.getString(4));
                tutors.add(tutor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Closer.close(connection);
        }
        return tutors;
    }
}

