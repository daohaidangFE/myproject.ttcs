package com.quanlydiemthi.backend.Controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ThongTinChungController {
    @GetMapping("/thongtinchung")
        public String thongtinchung(Model model) {
            return "/dashboard/thongtinchung";
        }
}
