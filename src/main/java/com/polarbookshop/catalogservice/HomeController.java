package com.polarbookshop.catalogservice;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String greeting(){
        return "Welcome to Book Catalog";
    }
}
