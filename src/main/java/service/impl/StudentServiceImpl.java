package service.impl;

import dao.Impl.StudentDaoImpl;
import dao.StudentDao;
import model.Student;
import service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private static StudentDao dao = StudentDaoImpl.getInstance();
    private static StudentService service;
    private StudentServiceImpl(){}
    public static synchronized StudentService getInstance(){
        if(service == null){
            service = new StudentServiceImpl();
        }
        return service;
    }

    @Override
    public Long create(Student student) {
        return dao.create(student);
    }

    @Override
    public Student find(Long id) {
        return dao.read(id);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public void update(Student student) {
        dao.update(student);
    }

    @Override
    public Student authorise(String email, String password) {
        return dao.authorise(email, password);
    }

    @Override
    public List<Student> getAll() {
        return dao.getAll();
    }

    @Override
    public void changeBanStatus(Long id) {
        dao.changeBanStatus(id);
    }
}
