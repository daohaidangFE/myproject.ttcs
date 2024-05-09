package com.quanlydiemthi.backend.Controller.admin;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.stereotype.Controller;

@Controller
public class HomeController {

    @GetMapping("/admin")
    public String home(Model model) {
        return "/dashboard/index";
    }

    @GetMapping("/forgotpassword")
    public String forgotpassword(Model model) {return "/public/forgotpassword";}

}

