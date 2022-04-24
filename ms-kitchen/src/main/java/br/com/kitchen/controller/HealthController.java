package br.com.kitchen.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kitchen/health")
public class HealthController {

    @GetMapping("check")
    public ResponseEntity<String> check(){
        return ResponseEntity.ok("ms-kitchen is running");
    }
}
