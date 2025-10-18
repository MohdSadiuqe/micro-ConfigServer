package com.usersService.usersService.ExternalService;

import com.usersService.usersService.Entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "RATINGSERVICE")
public interface RatingService {

    @PostMapping("/ratings")
    ResponseEntity<Rating> createRating(@RequestBody Rating values);

    @PutMapping("/ratings/{ratingId}")
    ResponseEntity<Rating> updateRating(@PathVariable("ratingId") String ratingId,
                        @RequestBody Rating rating);

    @DeleteMapping("/ratings/{ratingId}")
    void deleteRating(@PathVariable("ratingId") String ratingId);
}
