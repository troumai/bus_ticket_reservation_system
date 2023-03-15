package kz.iitu.itse1903.abimoldayeva.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.iitu.itse1903.abimoldayeva.model.Bus;
import kz.iitu.itse1903.abimoldayeva.model.Role;
import kz.iitu.itse1903.abimoldayeva.model.User;
import kz.iitu.itse1903.abimoldayeva.model.dto.RoleDTO;
import kz.iitu.itse1903.abimoldayeva.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<User> createUser(@RequestBody User user){
        try{
            User createdUser = userService.save(new User(user.getLogin(), user.getPassword(),user.getFullName()));
            return new ResponseEntity(createdUser, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER') or hasAuthority('DRIVER')")
    public ResponseEntity<User> getUserById(@AuthenticationPrincipal User user, @PathVariable("id") long id){
        User userById = userService.getUserById(id);
        return ResponseEntity.ok(userById);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> setUserRole(@RequestBody RoleDTO roleDTO,  @PathVariable("id") long id){
        User userWithUpdatedRole = userService.addRoleToUser(id, roleDTO.getRole());
        return ResponseEntity.ok(userWithUpdatedRole);
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<User>> getUserList(){
        List<User> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id){
        try{
            userService.delete(id);
            return new ResponseEntity<>("User with id: " + id + " successfully deleted", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
