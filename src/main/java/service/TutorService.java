package service;

import model.Tutor;
import model.User;

import java.util.List;

public interface TutorService {
    Long create(Tutor user);
    Tutor find(Long id);
    void update(Tutor user);
    User authorise(String email, String password);
    List<Tutor> getAll();

}
