package service.impl;


import dao.Impl.TopicDao;
import model.Topic;

import java.util.List;

public class TopicService {
    private static TopicDao dao = TopicDao.getInstance();
    private static TopicService service;
    private TopicService(){}
    public static synchronized TopicService getInstance(){
        if(service == null){
            service = new TopicService();
        }
        return service;
    }

    public List<Topic> getAll(){
        return dao.getAll();
    }
}
