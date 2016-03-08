package dao;

import model.Tutor;
import model.User;

import java.util.List;

public interface TutorDao {
    Long create(Tutor tutor);
    Tutor read(Long id);
    void update(Tutor tutor);
    User authorise(String email, String password);
    List<Tutor> getAll();
}
