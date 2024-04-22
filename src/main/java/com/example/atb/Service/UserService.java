package com.example.atb.Service;

import com.example.atb.Entities.Response.UserResponse;
import com.example.atb.Entities.User;

import java.util.List;

public interface UserService {
    List<UserResponse> getUser();
    UserResponse updateUser(long id,User user);

    User getUserById(long userId);
}
