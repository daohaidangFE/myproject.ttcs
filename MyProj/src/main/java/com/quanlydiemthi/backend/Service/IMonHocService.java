package com.quanlydiemthi.backend.Service;

import com.quanlydiemthi.backend.Entity.MonHoc;
import com.quanlydiemthi.backend.Payloads.MonHocDTO;

import java.util.List;

public interface IMonHocService {
    List<MonHocDTO> findAll();
    List<MonHocDTO> searchMonHocByTenMH(String tenMH);
    MonHocDTO createMonHoc(MonHocDTO monHocDTO);
    MonHocDTO updateMonHoc(MonHocDTO monHocDTO);
    MonHoc findMonHoc(String maMH);
}
