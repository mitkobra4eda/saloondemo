package com.ddimitko.prototype.controllers;

import com.ddimitko.prototype.objects.Booking;
import com.ddimitko.prototype.objects.Shop;
import com.ddimitko.prototype.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    ShopService shopService;

    @GetMapping("/home")
    private String home(Model model){

        return "home";
    }

    @GetMapping("/shops")
    private String listShops(Model model){

        List<Shop> shop = shopService.findAll();

        model.addAttribute("shop", shop);

        return "shops";
    }

}
