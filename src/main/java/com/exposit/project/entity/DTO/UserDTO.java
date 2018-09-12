package com.exposit.project.entity.DTO;

import com.exposit.project.entity.User;

import java.util.Objects;

public class UserDTO {
    private String name;
    private Integer age;
    private Long id;

    public UserDTO(String name, Integer age, Long id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public UserDTO(String name) {
        this.name = name;
    }
    public UserDTO() {}

    public UserDTO(User user) {
        this.name = user.getName();
        this.age = user.getAge();
        this.id = user.getId();
    }

    public User convertToUser(){
        if (Objects.nonNull(name)){
            User user = new User();
            user.setName(name);
            user.setAge(age);
            if (Objects.nonNull(id)){
                user.setId(id);
            }
            return user;
        }
        throw new RuntimeException("Name can't be blank!");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
