package com.quanlydiemthi.backend.Controller.admin;


import com.quanlydiemthi.backend.Entity.GiangVien;
import com.quanlydiemthi.backend.Payloads.KhoaDTO;
import com.quanlydiemthi.backend.Service.IGiangVienService;
import com.quanlydiemthi.backend.Service.Impl.KhoaServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class KhoaController {
    @Autowired
    private KhoaServiceImpl khoaService;
    @Autowired
    private IGiangVienService giangVienService;

    @GetMapping("/khoachuyenmon")
    public String getAllKhoa(Model model, HttpSession session) {
        Object loggedInUser = session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            String username;
            if (loggedInUser instanceof GiangVien giangVien) {
                username = giangVien.getUsername();
                GiangVien giangVienlog = giangVienService.findByUserName(username);
                model.addAttribute("giangVienlog", giangVienlog);
            }
        }
        List<KhoaDTO> khoaDTOList = khoaService.findAll();
        model.addAttribute("khoaDTOList", khoaDTOList);
        return "/dashboard/khoachuyenmon";
    }

    @GetMapping("/api/khoa/{id}")
    public ResponseEntity<?> getKhoaById(@PathVariable Integer id) {
        KhoaDTO khoaDTO = khoaService.findKhoaById(id);
        return ResponseEntity.ok(khoaDTO);
    }

    @GetMapping("/api/khoa/searchbyname")
    public ResponseEntity<?> searchKhoaByTenK(@RequestParam String searchValue) {
        List<KhoaDTO> khoaDTOList = khoaService.findByTenKhoa(searchValue.replaceAll("\s\s+", " ").trim());
        return ResponseEntity.ok(khoaDTOList);
    }
}