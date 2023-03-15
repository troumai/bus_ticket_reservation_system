package kz.iitu.itse1903.abimoldayeva.service;

import kz.iitu.itse1903.abimoldayeva.model.Role;
import kz.iitu.itse1903.abimoldayeva.model.Trip;
import kz.iitu.itse1903.abimoldayeva.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User save(User user);
    User getUserById(Long id);
    List<User> getAll();
    User update(Long id, User user);
    void delete(Long id);
    User addRoleToUser(Long id, Role role);
}
