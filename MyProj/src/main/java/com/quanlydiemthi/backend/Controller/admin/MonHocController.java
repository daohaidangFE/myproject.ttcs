package com.quanlydiemthi.backend.Controller.admin;


import com.quanlydiemthi.backend.Payloads.MonHocDTO;
import com.quanlydiemthi.backend.Service.Impl.MonHocServiceImpl;
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

    @GetMapping("/ttmonhoc")
    public String getAllMonHoc(Model model) {
        List<MonHocDTO> monHocDTOList = monHocService.findAll();
        model.addAttribute("monHocDTOList", monHocDTOList);
        return "/dashboard/ttmonhoc";
    }

    @GetMapping("/api/monhoc/{id}")
    public ResponseEntity<?> getMonHocById(@PathVariable Integer id) {
        MonHocDTO monHocDTO = monHocService.findMonHocById(id);
        return ResponseEntity.ok(monHocDTO);
    }

    @GetMapping("/api/monhoc/searchbyname")
    public ResponseEntity<?> getMonHocByName(@RequestParam String searchValue) {
        List<MonHocDTO> monHocDTOList = monHocService.searchMonHocByTenMH(searchValue.replaceAll("\s\s+", " ").trim());
        return ResponseEntity.ok(monHocDTOList);
    }

}
