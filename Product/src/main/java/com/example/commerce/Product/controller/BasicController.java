package com.example.commerce.Product.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/basic")
public class BasicController {

    @GetMapping("/pinging")
    public String getPing(){
        return "Hello world";
    }
    @GetMapping("/ping/{val}")
    public String gtValuedPing(@PathVariable(name = "val") String val){
        return ("we've got value :: "+ val);
    }
}
