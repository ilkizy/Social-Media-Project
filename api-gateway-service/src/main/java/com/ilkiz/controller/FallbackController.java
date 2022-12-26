package com.ilkiz.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class FallbackController {

    @GetMapping("/fallback-user-service")
    public ResponseEntity<String> fallbackUser(){
        return ResponseEntity.ok("User service not available");
    }
    @GetMapping("/fallback-auth-service")
    public ResponseEntity<String> fallbackAuth(){
        return ResponseEntity.ok("Auth service not available");
    }
    @GetMapping("/fallback-post-service")
    public ResponseEntity<String> fallbackPost(){
        return ResponseEntity.ok("Post service not available");
    }
}
