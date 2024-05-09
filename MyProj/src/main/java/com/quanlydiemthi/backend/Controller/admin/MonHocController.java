package com.quanlydiemthi.backend.Controller.admin;


import com.quanlydiemthi.backend.Entity.GiangVien;
import com.quanlydiemthi.backend.Payloads.MonHocDTO;
import com.quanlydiemthi.backend.Service.IGiangVienService;
import com.quanlydiemthi.backend.Service.Impl.MonHocServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class MonHocController {
    @Autowired
    private MonHocServiceImpl monHocService;
    @Autowired
    private IGiangVienService giangVienService;

    @GetMapping("/ttmonhoc")
    public String getAllMonHoc(Model model, HttpSession session) {
        List<MonHocDTO> monHocDTOList = monHocService.findAll();
        model.addAttribute("monHocDTOList", monHocDTOList);
        Object loggedInUser = session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            String username;
            if (loggedInUser instanceof GiangVien giangVien) {
                username = giangVien.getUsername();
                GiangVien giangVienlog = giangVienService.findByUserName(username);
                model.addAttribute("giangVienlog", giangVienlog);
            }
        }
        return "/dashboard/ttmonhoc";
    }

    @GetMapping("/api/monhoc/searchbyname")
    public ResponseEntity<?> getMonHocByName(@RequestParam String searchValue) {
        List<MonHocDTO> monHocDTOList = monHocService.searchMonHocByTenMH(searchValue.replaceAll("\s\s+", " ").trim());
        return ResponseEntity.ok(monHocDTOList);
    }

}
