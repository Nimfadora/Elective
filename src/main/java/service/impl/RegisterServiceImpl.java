package service.impl;

import dao.Impl.RegisterDaoImpl;
import dao.RegisterDao;
import model.Record;
import model.Register;
import service.RegisterService;

public class RegisterServiceImpl implements RegisterService {
    private static RegisterDao dao = RegisterDaoImpl.getInstance();
    private static RegisterService service;
    private RegisterServiceImpl(){}
    public static synchronized RegisterService getInstance(){
        if(service == null){
            service = new RegisterServiceImpl();
        }
        return service;
    }
    @Override
    public void addStudent(Record record) {
        dao.addStudent(record);
    }

    @Override
    public void updateRegister(Register register) {
        dao.updateRegister(register);
    }

    @Override
    public Register getRegister(Long courseId) {
        return dao.getRegister(courseId);
    }

    @Override
    public Record getMark(Record record) {
        return dao.getMark(record);
    }

}
