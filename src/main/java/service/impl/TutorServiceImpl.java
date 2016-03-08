package service.impl;

import dao.Impl.TutorDaoImpl;
import dao.TutorDao;
import model.Tutor;
import model.User;
import service.TutorService;

import java.util.List;

public class TutorServiceImpl implements TutorService {
    private static TutorDao dao = TutorDaoImpl.getInstance();
    private static TutorService service;
    private TutorServiceImpl(){}
    public static synchronized TutorService getInstance(){
        if(service == null){
            service = new TutorServiceImpl();
        }
        return service;
    }
    @Override
    public Long create(Tutor user) {
        return dao.create(user);
    }

    @Override
    public Tutor find(Long id) {
        return dao.read(id);
    }

    @Override
    public void update(Tutor user) {
        dao.update(user);
    }

    @Override
    public User authorise(String email, String password) {
        return dao.authorise(email, password);
    }

    @Override
    public List<Tutor> getAll() {
        return dao.getAll();
    }
}
