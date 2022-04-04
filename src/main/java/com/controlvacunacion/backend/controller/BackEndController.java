package com.controlvacunacion.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BackEndController {
    @RequestMapping("/")
    public String welcomeMessage() {
        return "index.html";
    }
}