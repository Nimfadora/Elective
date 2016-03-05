package service;

import model.Record;
import model.Register;

import java.util.List;

public interface RegisterService {
    void addStudent(Record record);
    void updateRegister(Register register);
    Register getRegister(Long courseId);
    Record getMark(Record record);
}
