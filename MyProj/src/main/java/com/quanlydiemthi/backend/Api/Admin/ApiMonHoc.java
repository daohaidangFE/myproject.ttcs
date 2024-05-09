package com.quanlydiemthi.backend.Api.Admin;


import com.quanlydiemthi.backend.Entity.Diem;
import com.quanlydiemthi.backend.Entity.MonHoc;
import com.quanlydiemthi.backend.Payloads.DiemDTO;
import com.quanlydiemthi.backend.Payloads.MonHocDTO;
import com.quanlydiemthi.backend.Payloads.SinhVienDTO;
import com.quanlydiemthi.backend.Service.IDiemService;
import com.quanlydiemthi.backend.Service.IMonHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiMonHoc {

    @Autowired
    private IMonHocService monHocService;

//    @DeleteMapping("/ttmonhoc/{maMH}")
//    public void deleteMonHoc(@PathVariable String maMH) {
//
//    }

    @PostMapping("/ttmonhoc")
    public ResponseEntity<?> addMonHoc(@RequestBody MonHocDTO monHocDTO) {
        MonHocDTO createMonHoc = monHocService.createMonHoc(monHocDTO);
        return ResponseEntity.ok(createMonHoc);
    }

    @PostMapping("/ttmonhoc/{maMH}")
    public String updateMonHoc(@RequestBody MonHocDTO monHocDTO) {
        try {
            monHocService.updateMonHoc(monHocDTO);
            return "success";
        } catch (Exception e) {
            return  "error";
        }
    }

    @GetMapping("/ttmonhoc/{maMH}")
    public ResponseEntity<?> getMonHocById(@PathVariable String maMH) {
        MonHoc monHoc = monHocService.findMonHoc(maMH);
        if(monHoc != null) {
            return ResponseEntity.ok(monHoc);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
