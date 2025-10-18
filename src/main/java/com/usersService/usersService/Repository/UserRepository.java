package com.usersService.usersService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.usersService.usersService.Entities.User;

public interface UserRepository extends JpaRepository<User, String> {

}
