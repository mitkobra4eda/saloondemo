package com.ddimitko.prototype.controllers;

import com.ddimitko.prototype.objects.*;
import com.ddimitko.prototype.services.BookingService;
import com.ddimitko.prototype.services.ShopService;
import com.ddimitko.prototype.services.UserService;

import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.threeten.extra.Interval;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Controller
public class BookingController {

    @Autowired
    UserService userService;

    @Autowired
    ShopService shopService;

    @Autowired
    BookingService bookingService;

    @GetMapping("/book")
    public String showAvailableBookings(@RequestParam Long shopId, @RequestParam Long serviceId, @RequestParam String staffId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, Model model){

        Shop shop = new Shop();
        if (shopService.findById(shopId).isPresent()) {
           shop = shopService.findById(shopId).get();
        }

        Services service = new Services();
        if (shopService.findByServiceId(serviceId).isPresent()) {
            service = shopService.findByServiceId(serviceId).get();
        }

        User staff = new User();
        if(userService.findByStaffId(staffId).isPresent()){
            staff = userService.findByStaffId(staffId).get();
        }


        LinkedHashMap<LocalTime, LocalTime> slots = bookingService.addSlots(service, staff, date);

        model.addAttribute("shopInfo", shop);
        model.addAttribute("date", date);
        model.addAttribute("slots", slots);
        model.addAttribute("serviceId", serviceId);
        model.addAttribute("staffId", staffId);

        return "available";
    }

    @PostMapping("/book")
    public String book(@RequestParam Long shopId, @RequestParam String staffId, @RequestParam Long userId, @RequestParam Long serviceId, @RequestParam String username, Booking booking) throws Exception {

        bookingService.createBooking(shopId, staffId, userId, serviceId, username, booking);

        return "redirect:/shops";
    }

    @PutMapping("/changeBooking")
    @ResponseStatus(value = HttpStatus.OK)
    public void changeBooking(@RequestParam(value="bookingId") Long bookingId,
                              @RequestParam(value="startTime") @DateTimeFormat(pattern = "HH:mm") LocalTime start,
                              @RequestParam(value="endTime") @DateTimeFormat(pattern = "HH:mm") LocalTime end) throws Exception {

        bookingService.changeBooking(bookingId, start, end);

    }

}
