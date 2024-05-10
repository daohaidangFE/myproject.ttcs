package com.quanlydiemthi.backend.Api.Admin;

import com.quanlydiemthi.backend.Entity.GiangVien;
import com.quanlydiemthi.backend.Entity.SinhVien;
import com.quanlydiemthi.backend.Payloads.GiangVienDTO;
import com.quanlydiemthi.backend.Payloads.SinhVienDTO;
import com.quanlydiemthi.backend.Service.IGiangVienService;
import com.quanlydiemthi.backend.Service.ISinhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiGiangVien {
    @Autowired
    private IGiangVienService giangVienService;

    @DeleteMapping("/giangvien/{maGV}")
    public void deleteGiangVien(@PathVariable String maGV) {
        giangVienService.deleteTeacher(maGV);
    }

    @PostMapping("/giangvien")
    public ResponseEntity<?> addGiangVien(@RequestBody GiangVienDTO giangVienDTO) {
        GiangVienDTO createGiangVien = giangVienService.createTeacher(giangVienDTO);
        return ResponseEntity.ok().body(createGiangVien);
    }

    @PostMapping("/giangvien/{maGV}")
    public String updateTeacher(@RequestBody GiangVienDTO giangVien) {
        try {
            giangVienService.updateTeacher(giangVien);
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/giangvien/{maGV}")
    public ResponseEntity<GiangVien> getTeacherById(@PathVariable String maGV) {
        GiangVien giangVien = giangVienService.findTeacher(maGV);
        if (giangVien != null) {
            return ResponseEntity.ok(giangVien);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
