package com.quanlydiemthi.backend.Controller.admin;


import com.quanlydiemthi.backend.Payloads.LopDTO;
import com.quanlydiemthi.backend.Service.Impl.LopServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LopController {

    @Autowired
    private LopServiceImpl lopService;

    @GetMapping("/api/lop")
    public ResponseEntity<?> getAllLop() {
        List<LopDTO> lopDTOList = lopService.findAll();
        return ResponseEntity.ok(lopDTOList);
    }

    @GetMapping("/api/lop/searchbyname")
    public ResponseEntity<?> getLopByName(@RequestParam String searchValue) {
        List<LopDTO> lopDTOList = lopService.searchByTenLop(searchValue.replaceAll("\s\s+", " ").trim());
        return ResponseEntity.ok(lopDTOList);
    }

    @GetMapping("/api/lop/{id}")
    public ResponseEntity<?> getLopById(@PathVariable Integer id) {
        LopDTO lopDTO = lopService.findLopById(id);
        return ResponseEntity.ok(lopDTO);
    }
}
