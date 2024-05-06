package com.quanlydiemthi.backend.Service;

import com.quanlydiemthi.backend.Entity.MonHoc;
import com.quanlydiemthi.backend.Payloads.MonHocDTO;

import java.util.List;

public interface IMonHocService {
    List<MonHocDTO> findAll();
    MonHocDTO findMonHocById(Integer Id);
    List<MonHocDTO> searchMonHocByTenMH(String tenMH);
}
