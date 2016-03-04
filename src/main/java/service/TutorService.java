package service;

import model.Tutor;

import java.util.List;

public interface TutorService {
    Long create(Tutor user);
    Tutor find(Long id);
    void delete(Long id);
    void update(Tutor user);
    Tutor authorise(String email, String password);
    List<Tutor> getAll();

}
