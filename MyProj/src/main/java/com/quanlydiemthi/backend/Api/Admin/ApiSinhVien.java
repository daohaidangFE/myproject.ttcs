package com.quanlydiemthi.backend.Api.Admin;

import com.quanlydiemthi.backend.Entity.SinhVien;
import com.quanlydiemthi.backend.Payloads.GiangVienDTO;
import com.quanlydiemthi.backend.Payloads.SinhVienDTO;
import com.quanlydiemthi.backend.Service.ISinhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api")
public class ApiSinhVien {
    @Autowired
    private ISinhVienService sinhVienService;

    @DeleteMapping("/sinhvien/{maSV}")
    public void deleteSinhVien(@PathVariable String maSV) {
        sinhVienService.deleteStudent(maSV);
    }

    @PostMapping("/addSinhvien")
    public ResponseEntity<?> addSinhVien(@RequestBody SinhVienDTO sinhVienDTO) {
        SinhVienDTO createSinhVien = sinhVienService.createStudent(sinhVienDTO);
        return ResponseEntity.ok().body(createSinhVien);
    }
    @PostMapping("/sinhvien/{maSV}")
    public String updateStudent(@RequestBody SinhVienDTO sinhVien) {
        try {
            sinhVienService.updateStudent(sinhVien);
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }
    @GetMapping("/sinhvien/{maSV}")
    public ResponseEntity<SinhVienDTO> getStudentById(@PathVariable String maSV) {
        SinhVienDTO sinhVien = sinhVienService.findStudent(maSV);
        if (sinhVien != null) {
            return ResponseEntity.ok(sinhVien);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}