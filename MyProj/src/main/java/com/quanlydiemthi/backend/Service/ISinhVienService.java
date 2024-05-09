package com.quanlydiemthi.backend.Service;

import com.quanlydiemthi.backend.Entity.SinhVien;
import com.quanlydiemthi.backend.Payloads.SinhVienDTO;

import java.util.List;
import java.util.Map;

public interface ISinhVienService {
//    void deleteSinhVienById(Integer Id);
    List<SinhVienDTO> findStudents(Map<String, String> params);
    void deleteStudent(String id);
    SinhVienDTO createStudent(SinhVienDTO studentDTO);
    SinhVien findStudent(String maSV);
    void updateStudent(SinhVienDTO sinhVienDTO);
    SinhVien findByUsername(String userName);
}
