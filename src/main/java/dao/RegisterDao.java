package dao;

import model.Record;
import model.Register;

import java.util.List;

public interface RegisterDao {
    void addStudent(Record record);
    void updateRegister(Register register);
    Register getRegister(Long courseId);
    Record getMark(Record record);
}
