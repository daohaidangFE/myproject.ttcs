package com.quanlydiemthi.backend.Controller.admin;

import com.quanlydiemthi.backend.Entity.GiangVien;
import com.quanlydiemthi.backend.Service.IGiangVienService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.stereotype.Controller;

@Controller
public class HomeController {
    @Autowired
    private IGiangVienService giangVienService;

    @GetMapping("/admin")
    public String home(Model model, HttpSession session) {
        Object loggedInUser = session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            String username;
            if (loggedInUser instanceof GiangVien giangVien) {
                username = giangVien.getUsername();
                GiangVien giangVienlog = giangVienService.findByUserName(username);
                model.addAttribute("giangVienlog", giangVienlog);
            }
        }
        return "/dashboard/index";
    }

    @GetMapping("/forgotpassword")
    public String forgotpassword(Model model) {return "/public/forgotpassword";}

}