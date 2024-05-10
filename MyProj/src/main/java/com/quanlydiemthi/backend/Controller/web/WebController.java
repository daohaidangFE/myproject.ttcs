package com.quanlydiemthi.backend.Controller.web;

import com.quanlydiemthi.backend.Entity.Diem;
import com.quanlydiemthi.backend.Entity.GiangVien;
import com.quanlydiemthi.backend.Entity.SinhVien;
import com.quanlydiemthi.backend.Payloads.DiemDTO;
import com.quanlydiemthi.backend.Payloads.GiangVienDTO;
import com.quanlydiemthi.backend.Payloads.SinhVienDTO;
import com.quanlydiemthi.backend.Payloads.UserDTO;
import com.quanlydiemthi.backend.Repository.GiangVienRepository;
import com.quanlydiemthi.backend.Service.IDiemService;
import com.quanlydiemthi.backend.Service.IGiangVienService;
import com.quanlydiemthi.backend.Service.ILopService;
import com.quanlydiemthi.backend.Service.ISinhVienService;
import com.quanlydiemthi.backend.Service.Impl.DiemServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class WebController {
    @Autowired
    private ISinhVienService sinhVienService;

    @Autowired
    private IGiangVienService giangVienService;

    @Autowired
    private ILopService lopService;

    @Autowired
    private IDiemService diemService;


    @GetMapping("/home")
    public String home() {
        return "/user/sinhvien/indexsv";
    }

    @GetMapping("/homeGV")
    public String homeGV() {
        return "/user/giangvien/indexgv";
    }

    @GetMapping("/")
    public String loginForm() {
        return "/public/login";
    }

    @PostMapping("/login")
    public String login(UserDTO userDTO, HttpSession session) {
        if(userDTO.getUsername().startsWith("KM")){
            GiangVien giangVien = giangVienService.findByUserName(userDTO.getUsername());
            if (giangVien != null && giangVien.getUsername().equals(userDTO.getUsername()) && giangVien.getPassword().equals(userDTO.getPassword())) {
                session.setAttribute("loggedInUser", giangVien);
                return "redirect:/homeGV";
            } else {
                return "redirect:/";
            }
        }
        else if (userDTO.getUsername().startsWith("ADMIN")){
            GiangVien giangVien = giangVienService.findByUserName(userDTO.getUsername());
            if (giangVien != null && giangVien.getUsername().equals(userDTO.getUsername()) && giangVien.getPassword().equals(userDTO.getPassword()) && giangVien.getRole().getRole().equals("admin")) {
                session.setAttribute("loggedInUser", giangVien);
                return "redirect:/admin";
            } else {
                return "redirect:/";
            }
        }
        else{
            SinhVien sinhVien = sinhVienService.findByUsername(userDTO.getUsername());
            if (sinhVien != null && sinhVien.getUsername().equals(userDTO.getUsername()) && sinhVien.getPassword().equals(userDTO.getPassword())) {
                session.setAttribute("loggedInUser", sinhVien);
                return "redirect:/home";
            } else {
                return "redirect:/";
            }
        }
    }

    @GetMapping("/chiTietDiem-{maSV}")
    public String detailDiem(Model model, HttpSession session, @PathVariable String maSV) {
        Object loggedInUser = session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            List<DiemDTO> diemDTOList = diemService.findAll(maSV);
            model.addAttribute("diemtList", diemDTOList);
            return "/user/giangvien/chiTietDiem";
        } else {
            return "redirect:/";
        }

    }
    //xem điểm của sinh viên
    @GetMapping("/diemthi")
    public String  getAllDiem(Model model, HttpSession session) {
        Object loggedInUser = session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            if (loggedInUser instanceof SinhVien sinhVien) {
                SinhVien sinhVien1 = sinhVienService.findByUsername(sinhVien.getUsername());
                List<DiemDTO> diemDTOList = diemService.findAll(sinhVien.getMaSV());
                model.addAttribute("diemtList", diemDTOList);
                model.addAttribute("sinhVien", sinhVien1);
                return "/user/sinhvien/diemthi";
            }
            else {
                return "redirect:/";
            }
        } else {
            return "redirect:/";
        }
    }

    //xem điểm chi tiết
    @GetMapping("/diemthi/{Id}")
    public ResponseEntity<DiemDTO> getDiembyId(@PathVariable Integer Id) {
        DiemDTO diem = diemService.findDiem(Id);
        if (diem != null) {
            return ResponseEntity.ok(diem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //xem ttcn của sinh viên
    @GetMapping("/ttcanhan")
    public String getTtcanhan(Model model, HttpSession session) {
        Object loggedInUser = session.getAttribute("loggedInUser");

        if (loggedInUser != null) {

            String username;
            if (loggedInUser instanceof SinhVien sinhVien) {

                username = sinhVien.getUsername();
                SinhVien sinhVienlog = sinhVienService.findByUsername(username);
                model.addAttribute("sinhVienlog", sinhVienlog);
                return "/user/sinhvien/ttcanhan";
            }  else {
                return "redirect:/";
            }
        } else {
            return "redirect:/";
        }

    }

    //xem ttcn cuả giảng viên
    @GetMapping("/ttcanhanGV")
    public String getTtcanhanGV(Model model, HttpSession session) {
        // Lấy thông tin đăng nhập từ session
        Object loggedInUser = session.getAttribute("loggedInUser");

        if (loggedInUser != null) {

            String username;
            if (loggedInUser instanceof GiangVien giangVien) {

                username = giangVien.getUsername();
                GiangVien giangVienlog = giangVienService.findByUserName(username);
                model.addAttribute("giangVienlog", giangVienlog);
                return "/user/giangvien/ttcanhan";
            }
            else {
                return "redirect:/";
            }
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/lop")
    public String getAllSinhVien(@RequestParam Map<String, String> params, Model model, HttpSession session) {

        Object loggedInUser = session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            String username;
            if (loggedInUser instanceof GiangVien giangVien) {
                username = giangVien.getUsername();
                GiangVien giangVienlog = giangVienService.findByUserName(username);
                params.put("maGV", giangVienlog.getMaGV());
                List<SinhVienDTO> sinhVienDTOList = sinhVienService.findStudents(params);
                model.addAttribute("sinhVienDTOList", sinhVienDTOList);
                model.addAttribute("giangVienlog", giangVienlog);
                return "/user/giangvien/lop";
            }
            else {
                return "redirect:/";
            }
        }
        else{
            return "redirect:/";
        }
    }
}