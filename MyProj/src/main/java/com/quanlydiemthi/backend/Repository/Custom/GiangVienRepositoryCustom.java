package com.quanlydiemthi.backend.Repository.Custom;

import com.quanlydiemthi.backend.Entity.GiangVien;

import java.util.List;
import java.util.Map;

public interface GiangVienRepositoryCustom {
    List<GiangVien> findTeachers(Map<String, String> conditions);
}
