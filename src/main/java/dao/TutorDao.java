package dao;

import model.Tutor;

import java.util.List;

public interface TutorDao {
    Long create(Tutor tutor);
    Tutor read(Long id);
    void update(Tutor tutor);
    void delete(Long id);
    Tutor authorise(String email, String password);
    List<Tutor> getAll();
}
