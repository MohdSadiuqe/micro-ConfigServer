package com.usersService.usersService.Service;

import com.usersService.usersService.Entities.User;
import java.util.List;

public interface UserService {
    // Save User
    User saveUser(User user);
    // Get All User
    List<User> getAllUsers();
    // Single User
    User getUser(String userId);
}
