package edu.fbansept.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String rienAvoir() {
        return "<h1>Le serveur marche mais y'a rien a voir ici</h1>";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello world";
    }

}
