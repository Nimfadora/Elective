package service;

import model.Student;

import java.util.List;

public interface StudentService {
    Long create(Student user);
    Student find(Long id);
    void delete(Long id);
    void update(Student user);
    Student authorise(String email, String password);
    List<Student> getAll();
    void changeBanStatus(Long id);
}
