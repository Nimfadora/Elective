package dao;
import model.Student;

import java.util.List;

public interface StudentDao {
    Long create(Student user);
    Student read(Long id);
    void update(Student user);
    void delete(Long id);
    Student authorise(String email, String password);
    List<Student> getAll();
    void changeBanStatus(Long id);
}
