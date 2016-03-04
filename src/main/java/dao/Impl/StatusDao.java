package dao.Impl;

import helper.Closer;
import service.impl.ConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class StatusDao {
    private static StatusDao dao;
    private StatusDao(){}
    public static synchronized StatusDao getInstance(){
        if(dao == null){
            dao = new StatusDao();
        }
        return dao;
    }

    private Connection connection;
    private PreparedStatement statement;

    private static final String GET_ALL = "SELECT * FROM STATUS";

    public List<String> getAll() {
        List<String> statuses = new LinkedList<>();
        try {
            connection = ConnectionService.getConnection();
            statement = connection.prepareStatement(GET_ALL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String status = rs.getString(1);
                statuses.add(status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Closer.close(connection);
        }
        return statuses;
    }
}
