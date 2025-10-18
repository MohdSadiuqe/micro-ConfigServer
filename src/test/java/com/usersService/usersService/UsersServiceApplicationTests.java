package com.usersService.usersService;

import com.usersService.usersService.Entities.Rating;
import com.usersService.usersService.ExternalService.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class UsersServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private RatingService ratingService;
//    @Test
//	void createRating(){
//		Rating rating=Rating.builder().rating(10).userId("").hotelId( "").feedback("This is creating using feign client").build();
//		ResponseEntity<Rating>ratingResponseEntity=ratingService.createRating(rating);
//		Rating ratingResponse=ratingResponseEntity.getBody();
//		System.out.println("Rating created successfully");
//	}
}
