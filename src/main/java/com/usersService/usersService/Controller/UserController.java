package com.usersService.usersService.Controller;

import com.usersService.usersService.Entities.Rating;
import com.usersService.usersService.Service.UserService;
import com.usersService.usersService.Entities.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger=LoggerFactory.getLogger(UserController.class);
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
    int retryCount=1;
    @GetMapping("/{userId}")
//    @CircuitBreaker(name="RatingHotelBreaker",fallbackMethod = "ratingHotelFallback")
//    @Retry(name="RatingHotelService",fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name="UserRateLimiter",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUser(@PathVariable String userId) {
        logger.info("Retry count: {}",retryCount++);
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
//        logger.info("RatingHotelBreaker method is called: {}", ex.getMessage());
        User user = User.builder()
                .email("dummy@gmail.com")
                .name("dummy")
                .about("This dummy user is created because some service is down")
                .userId("1234")
                .build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }
}
