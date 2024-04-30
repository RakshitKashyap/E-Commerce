package com.example.commerce.Product.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product/check")
@Slf4j
public class BasicController {

    @GetMapping("/ping")
    public String getPing(){
        return "Hello world!!";
    }

    @GetMapping("/ping/{val}")
    public String gtValuedPing(@PathVariable(name = "val")String val){
        return ("we've got value :: "+ val);
    }
}
