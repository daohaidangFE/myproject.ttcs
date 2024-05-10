package com.quanlydiemthi.backend.Service.Impl;


import com.quanlydiemthi.backend.Entity.Lop;
import com.quanlydiemthi.backend.Entity.SinhVien;
import com.quanlydiemthi.backend.Payloads.SinhVienDTO;
import com.quanlydiemthi.backend.Repository.LopRepository;
import com.quanlydiemthi.backend.Repository.SinhVienRepository;
import com.quanlydiemthi.backend.Service.ISinhVienService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class SinhVienServiceImpl implements ISinhVienService {
    @Autowired
    private SinhVienRepository sinhvienRepository;
    @Autowired
    private LopRepository lopRepository;

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
        sinhVien.setMaSV(sinhVienDTO.getMaSV().replaceAll("\s\s+", "").trim());
        sinhVien.setActive(true);
        sinhVien.setTenSV(sinhVienDTO.getTenSV().replaceAll("\s\s+", " ").trim());
        sinhVien.setGioiTinh(sinhVienDTO.getGioiTinh().replaceAll("\s\s+", " ").trim());
        sinhVien.setUsername(sinhVienDTO.getUsername().replaceAll("\s\s+", " ").trim());
        sinhVien.setPassword(sinhVienDTO.getPassword().replaceAll("\s\s+", " ").trim());
        sinhVien.setEmail(sinhVienDTO.getEmail().replaceAll("\s\s+", " ").trim());
        Lop lop = lopRepository.findByMaLop(sinhVienDTO.getMaLop());
        sinhVien.setLop(lop);
        sinhvienRepository.save(sinhVien);
        return this.modelMapper.map(sinhVien, SinhVienDTO.class);
    }

    @Override
    public SinhVienDTO findStudent(String maSV) {
        SinhVien sinhVien = sinhvienRepository.findByMaSV(maSV);
        SinhVienDTO sinhVienDTO = new SinhVienDTO();
        sinhVienDTO.setMaSV(maSV);
        sinhVienDTO.setMaLop(sinhVien.getLop().getMaLop());
        sinhVienDTO.setTenSV(sinhVien.getTenSV());
        sinhVienDTO.setUsername(sinhVien.getUsername());
        sinhVienDTO.setGioiTinh(sinhVien.getGioiTinh());
        sinhVienDTO.setTenLop(sinhVien.getLop().getTenLop());
        sinhVienDTO.setEmail(sinhVien.getEmail());
        return sinhVienDTO;
    }

    @Override
    public void updateStudent(SinhVienDTO sinhVienDTO) {
        SinhVien sinhVien = sinhvienRepository.findByMaSV(sinhVienDTO.getMaSV());
        sinhVien.setTenSV(sinhVienDTO.getTenSV().replaceAll("\s\s+", " ").trim());
        sinhVien.setGioiTinh(sinhVienDTO.getGioiTinh().replaceAll("\s\s+", " ").trim());
        sinhVien.setUsername(sinhVienDTO.getUsername().replaceAll("\s\s+", " ").trim());
        sinhVien.setEmail(sinhVienDTO.getEmail().replaceAll("\s\s+", " ").trim());
        Lop lop = lopRepository.findByMaLop(sinhVienDTO.getMaLop());
        sinhVien.setLop(lop);
        sinhvienRepository.save(sinhVien);
        this.modelMapper.map(sinhVien, SinhVienDTO.class);
    }

    @Override
    public SinhVien findByUsername(String userName) {
        return sinhvienRepository.findByMaSV(userName);
    }
}