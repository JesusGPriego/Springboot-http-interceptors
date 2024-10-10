package com.suleware.springboot.app.interceptor.springboot_interceptor.controllers;

import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/foo")
public class AppController {

    @GetMapping()
    public ResponseEntity<Map<String, Object>> foo() {
        Map<String, Object> json = Collections.singletonMap("message", "handler foo from controller");

        return ResponseEntity.status(HttpStatus.OK.value()).body(json);
    }

}
