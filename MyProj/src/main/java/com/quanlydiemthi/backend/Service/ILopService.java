package com.quanlydiemthi.backend.Service;

import com.quanlydiemthi.backend.Entity.SinhVien;
import com.quanlydiemthi.backend.Payloads.LopDTO;

import java.util.List;

public interface ILopService {
    List<LopDTO> findAll();
    LopDTO findLopById(Integer Id);
    List<LopDTO> searchByTenLop(String tenLop);
    List<SinhVien> findAllSinhVienByLop(String maLop);
}
