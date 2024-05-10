package com.quanlydiemthi.backend.Service;


import com.quanlydiemthi.backend.Entity.Diem;
import com.quanlydiemthi.backend.Entity.MonHoc;
import com.quanlydiemthi.backend.Payloads.DiemDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IDiemService {
    List<DiemDTO> findAll(String maSV);
    List<DiemDTO> findByMaSV(String maSV);
    void deleteDiem(Integer Id);
    DiemDTO findDiem(Integer Id);
    void createDiem(DiemDTO diemDTO);
    void updateDiem(DiemDTO diemDTO);
}