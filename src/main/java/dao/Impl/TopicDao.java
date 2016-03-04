package dao.Impl;

import helper.Closer;
import model.Topic;
import service.impl.ConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TopicDao {
    private static TopicDao dao;
    private TopicDao(){}
    public static synchronized TopicDao getInstance(){
        if(dao == null){
            dao = new TopicDao();
        }
        return dao;
    }

    private Connection connection;
    private PreparedStatement statement;

    private static final String GET_ALL = "SELECT * FROM TOPIC";

    public List<Topic> getAll() {
        List<Topic> topics = new LinkedList<>();
        try {
            connection = ConnectionService.getConnection();
            statement = connection.prepareStatement(GET_ALL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Topic topic = new Topic();
                topic.setId(rs.getLong(1));
                topic.setTitle(rs.getString(2));
                topics.add(topic);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Closer.close(connection);
        }
        return topics;
    }
}
