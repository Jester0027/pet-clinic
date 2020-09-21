package com.outlook.ned.paul.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/pets")
@Controller
public class VetController {

    @RequestMapping({"/", ""})
    public String index() {
        return "vets/index";
    }
}