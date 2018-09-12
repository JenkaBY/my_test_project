package com.exposit.project.service;

import com.exposit.project.entity.DTO.UserDTO;

import java.util.List;
import java.util.Optional;


public interface UserService {
    List<UserDTO> findAll();
    Optional<UserDTO> findById(Long id);
    Optional<UserDTO> findByName(String name);
    Optional<UserDTO> createNewUser(UserDTO user);

    UserDTO updateUser(UserDTO user);

    boolean isUserNameExist(String name);
}
