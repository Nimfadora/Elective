package service.impl;

import dao.Impl.StatusDao;

import java.util.List;

public class StatusService {
    private static StatusDao dao = StatusDao.getInstance();
    private static StatusService service;
    private StatusService(){}
    public static synchronized StatusService getInstance(){
        if(service == null){
            service = new StatusService();
        }
        return service;
    }

    public List<String> getAll(){
        return dao.getAll();
    }
}
