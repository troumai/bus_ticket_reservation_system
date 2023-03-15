package kz.iitu.itse1903.abimoldayeva.service.impl;

import kz.iitu.itse1903.abimoldayeva.model.Role;
import kz.iitu.itse1903.abimoldayeva.model.User;
import kz.iitu.itse1903.abimoldayeva.repository.UserRepository;
import kz.iitu.itse1903.abimoldayeva.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    @Transactional
    public User save(User user) {
        user.setActive(true);

        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        user.setRoles(roles);

        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User update(Long id, User user) {
        User updatedUser = userRepository.findById(id).get();

        updatedUser.setFullName(user.getFullName());
        updatedUser.setLogin(user.getLogin());
        updatedUser.setPassword(user.getPassword());

        return userRepository.save(updatedUser);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User addRoleToUser(Long id, Role role){
        User user = userRepository.findById(id).get();
        if (user.getRoles().isEmpty()){
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
        }else {
            user.addRole(role);
        }
        return userRepository.save(user);
    }
}
