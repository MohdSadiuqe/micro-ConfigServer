package com.usersService.usersService.Service.impl;

import com.usersService.usersService.Controller.UserController;
import com.usersService.usersService.Entities.Hotel;
import com.usersService.usersService.Entities.Rating;
import com.usersService.usersService.ExternalService.HotelService;
import com.usersService.usersService.Repository.UserRepository;
import com.usersService.usersService.Exception.ResourceNotFoundException;
import com.usersService.usersService.Service.UserService;
import com.usersService.usersService.Entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ImplUserServices implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger= LoggerFactory.getLogger(UserController.class);

    @Override
    public User saveUser(User user) {
        String RandomUserId=UUID.randomUUID().toString();
        user.setUserId(RandomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user= userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with id: " + userId));
        // http://localhost:8083/ratings/users/fc704d89-26d0-444c-883d-085e43859431
        Rating[] RatingOfUser= restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        logger.info("Rating of objects {} ",RatingOfUser);
        List<Rating>ratings=Arrays.stream(RatingOfUser).toList();
        user.setRatings(ratings);
        // http://localhost:8080/hotels/3b7b21a0-6d06-4dcc-ae48-882fe0fb2609
        List<Rating>RatingList= ratings.stream().map(rating->{
            // ResponseEntity<Hotel>ForEntity=restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(),Hotel.class);
            // Hotel hotel=ForEntity.getBody();
            Hotel hotel=hotelService.getHotel(rating.getHotelId());
            // logger.info("response status code {}",ForEntity.getStatusCode());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(RatingList);
        return user;
    }
}
