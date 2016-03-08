package dao;
import model.Student;
import model.User;
import java.util.List;

public interface StudentDao {
    Long create(Student user);
    Student read(Long id);
    void update(Student user);
    User authorise(String email, String password);
    List<Student> getAll();
    void changeBanStatus(Long id);
    Boolean getBanStatus(Long id);
}
