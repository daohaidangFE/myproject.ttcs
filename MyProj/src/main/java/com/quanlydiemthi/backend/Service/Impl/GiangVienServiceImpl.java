package com.quanlydiemthi.backend.Service.Impl;

import com.quanlydiemthi.backend.Entity.GiangVien;
import com.quanlydiemthi.backend.Exceptions.NotFoundException;
import com.quanlydiemthi.backend.Payloads.GiangVienDTO;
import com.quanlydiemthi.backend.Repository.GiangVienRepository;
import com.quanlydiemthi.backend.Service.IGiangVienService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class GiangVienServiceImpl implements IGiangVienService {
    @Autowired
    private GiangVienRepository giangVienRepository;

    @Autowired
    public ModelMapper modelMapper;

    @Override
    public List<GiangVienDTO> findTeachers(Map<String, String> params) {
        List<GiangVien> giangVienList = giangVienRepository.findTeachers(params);
        return giangVienList.stream()
                .map((giangvien) -> this.modelMapper.map(giangvien, GiangVienDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTeacher(String maGV) {
        GiangVien giangVien1 = giangVienRepository.findByMaGV(maGV.replaceAll("\\s+", "").trim());
        giangVien1.setActive(false);
        giangVienRepository.save(giangVien1);
    }

    @Override
    public GiangVienDTO createTeacher(GiangVienDTO giangVienDTO) {
        GiangVien giangVien = new GiangVien();
        giangVien.setMaGV(giangVienDTO.getMaGV().replaceAll("\\s+", "").trim());
        giangVien.setActive(true);
        giangVien.setTenGV(giangVienDTO.getTenGV().replaceAll("\s\s+", " ").trim());
        giangVien.setUsername(giangVienDTO.getUsername().replaceAll("\\s+", "").trim());
        giangVien.setPassword(giangVienDTO.getPassword().replaceAll("\\s+", "").trim());
        giangVien.setGioiTinh(giangVienDTO.getGioiTinh().replaceAll("\s\s+", " ").trim());
        giangVien.setEmail(giangVienDTO.getEmail().replaceAll("\\s+", "").trim());
        giangVienRepository.save(giangVien);
        return this.modelMapper.map(giangVien, GiangVienDTO.class);
    }

    @Override
    public GiangVien findTeacher(String maGV) {
        return giangVienRepository.findByMaGV(maGV);
    }

    @Override
    public GiangVien findByUserName(String userName) {
        return giangVienRepository.findByMaGV(userName);
    }

    @Override
    public void updateTeacher(GiangVienDTO giangVienDTO) {
        GiangVien giangVien = giangVienRepository.findByMaGV(giangVienDTO.getMaGV());
        giangVien.setTenGV(giangVienDTO.getTenGV().replaceAll("\s\s+", " ").trim());
        giangVien.setGioiTinh(giangVienDTO.getGioiTinh().replaceAll("\\s+", "").trim());
        giangVien.setUsername(giangVienDTO.getUsername().replaceAll("\\s+", "").trim());
        giangVien.setEmail(giangVienDTO.getEmail().replaceAll("\\s+", "").trim());
        giangVienRepository.save(giangVien);
        this.modelMapper.map(giangVien, GiangVienDTO.class);
    }

    @Override
    public void resetPassword(GiangVienDTO giangVienDTO) {
        GiangVien giangVien = giangVienRepository.findByMaGV(giangVienDTO.getMaGV());
        giangVien.setPassword(giangVienDTO.getNewPassword().replaceAll("\\s+", "").trim());
        giangVienRepository.save(giangVien);
        this.modelMapper.map(giangVien, GiangVienDTO.class);
    }
}

