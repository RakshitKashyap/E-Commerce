package com.example.Order_Service.Order.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

    @GetMapping("/v1/basic")
    public ResponseEntity checkBasicController(){
        String result = "<h1>ping.....ping...</h1>";
        return new ResponseEntity(result, HttpStatusCode.valueOf(200));
    }


}
