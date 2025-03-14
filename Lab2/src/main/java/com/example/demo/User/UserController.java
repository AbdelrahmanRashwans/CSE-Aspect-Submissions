package com.example.demo.User;

import com.example.demo.User.dto.CreateUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService){
        this.userService= userService;
    }

    @GetMapping()
    public List<User> getUsers(){
        return this.userService.getUsers();
    }

    @PostMapping()
    public User createUser(@RequestBody CreateUserDto createUserDto){
        return this.userService.createUser(createUserDto);
    }

    @GetMapping("/{id}")
    public Optional<User> findUserById(@PathVariable Integer id) {
        return this.userService.findUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody CreateUserDto updateUserDto) {
        return this.userService.updateUser(id, updateUserDto);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id) {
        return this.userService.deleteUser(id);
    }




}
