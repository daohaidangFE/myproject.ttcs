package com.quanlydiemthi.backend.Controller.admin;

import com.quanlydiemthi.backend.Entity.GiangVien;
import com.quanlydiemthi.backend.Payloads.SinhVienDTO;
import com.quanlydiemthi.backend.Service.IGiangVienService;
import com.quanlydiemthi.backend.Service.ISinhVienService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class SinhVienController {
    @Autowired
    private ISinhVienService sinhVienService;
    @Autowired
    private IGiangVienService giangVienService;

    @GetMapping("/sinhvien")
    public String getAllSinhVien(@RequestParam Map<String, String> params, Model model, HttpSession session) {
        Object loggedInUser = session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            String username;
            if (loggedInUser instanceof GiangVien giangVien) {
                username = giangVien.getUsername();
                GiangVien giangVienlog = giangVienService.findByUserName(username);
                if(!giangVien.getRole().getRole().equals("admin")) {
                    params.put("maGV", giangVienlog.getMaGV());
                }
                model.addAttribute("giangVienlog", giangVienlog);
            }
        }
        List<SinhVienDTO> sinhVienDTOList = sinhVienService.findStudents(params);
        model.addAttribute("sinhVienDTOList", sinhVienDTOList);
        return "/dashboard/sinhvien";
    }

}
