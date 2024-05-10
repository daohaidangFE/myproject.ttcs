package com.quanlydiemthi.backend.Api.Admin;

import com.quanlydiemthi.backend.Entity.SinhVien;
import com.quanlydiemthi.backend.Payloads.DiemDTO;
import com.quanlydiemthi.backend.Service.IDiemService;
import com.quanlydiemthi.backend.Service.ISinhVienService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiDiem {
    @Autowired
    private IDiemService diemService;
    @Autowired
    private ISinhVienService sinhVienService;

    @PostMapping("/addDiem")
    public String addSinhVien(@RequestBody DiemDTO diemDTO, HttpSession session, Model model) {
        try {

            diemService.createDiem(diemDTO);
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }
    @PostMapping("/updateDiem")
    public String updateStudent(@RequestBody DiemDTO diemDTO) {
        try {
            diemService.updateDiem(diemDTO);
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }
}