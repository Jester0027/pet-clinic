package com.outlook.ned.paul.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    @RequestMapping({"/", ""})
    public String index() {
        return "owners/index";
    }
}
