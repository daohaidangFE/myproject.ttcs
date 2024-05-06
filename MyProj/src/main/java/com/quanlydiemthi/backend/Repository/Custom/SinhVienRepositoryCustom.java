package com.quanlydiemthi.backend.Repository.Custom;

import com.quanlydiemthi.backend.Entity.SinhVien;

import java.util.List;
import java.util.Map;

public interface SinhVienRepositoryCustom {
    List<SinhVien> findStudents(Map<String, String> conditions);
}
