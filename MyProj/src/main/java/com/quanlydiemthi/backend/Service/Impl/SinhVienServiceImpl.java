package com.quanlydiemthi.backend.Service.Impl;


import com.quanlydiemthi.backend.Entity.SinhVien;
import com.quanlydiemthi.backend.Payloads.SinhVienDTO;
import com.quanlydiemthi.backend.Repository.SinhVienRepository;
import com.quanlydiemthi.backend.Service.ISinhVienService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SinhVienServiceImpl implements ISinhVienService {
    @Autowired
    private SinhVienRepository sinhvienRepository;

    @Autowired
    public ModelMapper modelMapper;

    @Override
    public List<SinhVienDTO> findStudents(Map<String, String> params) {
        List<SinhVien> sinhVienList = sinhvienRepository.findStudents(params);
        return sinhVienList.stream()
                .map((sinhvien) -> this.modelMapper.map(sinhvien, SinhVienDTO.class))
                .collect((Collectors.toList()));
    }

    @Override
    public void deleteStudent(String maSv) {
        SinhVien sinhVien = sinhvienRepository.findByMaSV(maSv.replaceAll("\s\s+", " ").trim());
        sinhVien.setActive(false);
        sinhvienRepository.save(sinhVien);
    }

    @Override
    public SinhVienDTO createStudent(SinhVienDTO sinhVienDTO) {
        SinhVien sinhVien = new SinhVien();
        sinhVien.setMaSV(sinhVienDTO.getMaSV().replaceAll("\s\s+", " ").trim());
        sinhVien.setActive(true);
        sinhVien.setTenSV(sinhVienDTO.getTenSV().replaceAll("\s\s+", " ").trim());
        sinhVien.setGioiTinh(sinhVienDTO.getGioiTinh().replaceAll("\s\s+", " ").trim());
        sinhVien.setUsername(sinhVienDTO.getUsername().replaceAll("\s\s+", " ").trim());
        sinhVien.setPassword(sinhVienDTO.getPassword().replaceAll("\s\s+", " ").trim());
        sinhVien.setEmail(sinhVienDTO.getEmail().replaceAll("\s\s+", " ").trim());
        sinhvienRepository.save(sinhVien);
        return this.modelMapper.map(sinhVien, SinhVienDTO.class);
    }

    @Override
    public SinhVien findStudent(String maSV) {
        return sinhvienRepository.findByMaSV(maSV);
    }
    @Override
    public void updateStudent(SinhVienDTO sinhVienDTO) {
        SinhVien sinhVien = sinhvienRepository.findByMaSV(sinhVienDTO.getMaSV());
        sinhVien.setTenSV(sinhVienDTO.getTenSV().replaceAll("\s\s+", " ").trim());
        sinhVien.setGioiTinh(sinhVienDTO.getGioiTinh().replaceAll("\s\s+", " ").trim());
        sinhVien.setUsername(sinhVienDTO.getUsername().replaceAll("\s\s+", " ").trim());
        sinhVien.setEmail(sinhVienDTO.getEmail().replaceAll("\s\s+", " ").trim());
        sinhvienRepository.save(sinhVien);
        this.modelMapper.map(sinhVien, SinhVienDTO.class);
    }
}
