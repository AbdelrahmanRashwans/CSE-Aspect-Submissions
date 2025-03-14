package com.example.demo.User;

import com.example.demo.User.dto.CreateUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository= userRepository;
    }

    public List<User> getUsers(){
        return this.userRepository.findAll();

    }
    public Optional<User> findUserById(Integer id) {
        return this.userRepository.findById(id);
    }


    public User createUser(CreateUserDto userDto){

        User user = new User(
                userDto.getEmail(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getPhoneNumber()
        );


        return this.userRepository.save(user);


    }

    public User updateUser(Integer id, CreateUserDto updateUserDto) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEmail(updateUserDto.getEmail());
            user.setUsername(updateUserDto.getUsername());
            user.setPassword(updateUserDto.getPassword());
            user.setPhoneNumber(updateUserDto.getPhoneNumber());

            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with id " + id);
        }
    }

    public String deleteUser(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User with ID " + id + " deleted successfully.";
        } else {
            return "User with ID " + id + " not found.";
        }
    }

}
