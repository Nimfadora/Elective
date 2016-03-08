package service;

import model.Student;
import model.User;

import java.util.List;

public interface StudentService {
    Long create(Student user);
    Student find(Long id);
    void update(Student user);
    User authorise(String email, String password);
    List<Student> getAll();
    void changeBanStatus(Long id);
    Boolean getBanStatus(Long id);
}
