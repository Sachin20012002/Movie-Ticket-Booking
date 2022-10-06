package com.sachin.MovieTicketBooking.service;

import com.sachin.MovieTicketBooking.entity.User;
import com.sachin.MovieTicketBooking.error.UserNotFoundException;
import com.sachin.MovieTicketBooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public User addUser(User user) {
       return userRepository.save(user);
    }

    @Override
    public User getUser(Long id) throws UserNotFoundException {
        Optional<User> user=userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("User not Found");
        }
        return user.get();
    }

    @Override
    public User updateUser(Long userId, User user) throws UserNotFoundException {
        if(userRepository.findById(userId).isEmpty()){
            throw new UserNotFoundException("User not Found");
        }
        User existingUser = userRepository.findById(userId).get();

        if(Objects.nonNull(user.getName()) &&
                !"".equalsIgnoreCase(user.getName())) {
            existingUser.setName(user.getName());
        }

        if(Objects.nonNull(user.getEmail()) &&
                !"".equalsIgnoreCase(user.getEmail())) {
            existingUser.setEmail(user.getEmail());
        }
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUserById(Long userId) throws UserNotFoundException {
        if(userRepository.findById(userId).isEmpty()){
            throw new UserNotFoundException("User not Found");
        }
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> fetchUserList() {
        return userRepository.findAll();
    }

    @Override
    public User fetchUserByEmail(String email) throws UserNotFoundException {
        if(userRepository.findByEmailIgnoreCase(email)==null){
            throw new UserNotFoundException("User not Found");
        }
        return userRepository.findByEmailIgnoreCase(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
