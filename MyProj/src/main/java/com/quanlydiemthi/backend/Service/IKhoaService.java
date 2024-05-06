package com.quanlydiemthi.backend.Service;

import com.quanlydiemthi.backend.Payloads.KhoaDTO;

import java.util.List;

public interface IKhoaService {
    List<KhoaDTO> findAll();
    KhoaDTO findKhoaById(Integer id);
    List<KhoaDTO> findByTenKhoa(String tenK);
}
