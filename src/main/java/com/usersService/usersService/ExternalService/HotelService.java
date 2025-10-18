package com.usersService.usersService.ExternalService;

import com.usersService.usersService.Entities.Hotel;
import jakarta.persistence.Table;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="HOTELSERVICE")
public interface HotelService  {
    @GetMapping("/hotels/{hotelId}")
    public Hotel getHotel(@PathVariable("hotelId") String hotelId);
}
