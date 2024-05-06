package com.quanlydiemthi.backend.Controller.admin;

import com.quanlydiemthi.backend.Entity.GiangVien;
import com.quanlydiemthi.backend.Payloads.GiangVienDTO;
import com.quanlydiemthi.backend.Payloads.Response.ApiResponse;
import com.quanlydiemthi.backend.Service.IGiangVienService;
import com.quanlydiemthi.backend.Service.Impl.GiangVienServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class GiangVienController {
    @Autowired
    private IGiangVienService giangVienService;

    @GetMapping("/giangvien")
    public String getAllGiangVien(@RequestParam Map<String, String> params, Model model) {
        List<GiangVienDTO> giangVienDTOList = giangVienService.findTeachers(params);
        model.addAttribute("giangVienDTOList", giangVienDTOList);
        return "/dashboard/giangvien";
    }

}
