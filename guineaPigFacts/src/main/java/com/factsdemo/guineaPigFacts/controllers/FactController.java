package com.factsdemo.guineaPigFacts.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fact")
public class FactController {
    @GetMapping("/created")
    public String createFact() {
        return "Fact Created";
    }
}
