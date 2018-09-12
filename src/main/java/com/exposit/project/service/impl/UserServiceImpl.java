package com.exposit.project.service.impl;

import com.exposit.project.entity.DTO.UserDTO;
import com.exposit.project.entity.User;
import com.exposit.project.repository.UserRepository;
import com.exposit.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> findById(Long id) {
        User foundUser = userRepository.getOne(id);
        return Optional.ofNullable(Objects.nonNull(foundUser) ? new UserDTO(foundUser) : null);
    }

    @Override
    public Optional<UserDTO> findByName(String name) {
        User foundUser = userRepository.findUserByName(name);
        return Optional.ofNullable(Objects.nonNull(foundUser) ? new UserDTO(foundUser) : null);
    }

    @Override
    public Optional<UserDTO> createNewUser(UserDTO user){
        if (isUserNameExist(user.getName())){
            return Optional.empty();
        }
        User convertedUser = user.convertToUser();
        UserDTO createdUserDto = new UserDTO(userRepository.save(convertedUser));
        return Optional.ofNullable(createdUserDto);
    }

    @Override
    public UserDTO updateUser(UserDTO user){
        User updatedUser = userRepository.save(user.convertToUser());
        return new UserDTO(updatedUser);
    }

    /**
     * Check if user exists with passed name
     * @param name checked name
     * @return true if user exists, false otherwise
     */
    @Override
    public boolean isUserNameExist(String name){
        User foundUser = userRepository.findUserByName(name);
        return Objects.nonNull(foundUser);
    }
}
