package com.quanlydiemthi.backend.Service.Impl;


import com.quanlydiemthi.backend.Entity.Lop;
import com.quanlydiemthi.backend.Entity.SinhVien;
import com.quanlydiemthi.backend.Exceptions.NotFoundException;
import com.quanlydiemthi.backend.Payloads.LopDTO;
import com.quanlydiemthi.backend.Repository.LopRepository;
import com.quanlydiemthi.backend.Repository.SinhVienRepository;
import com.quanlydiemthi.backend.Service.ILopService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LopServiceImpl implements ILopService {
    @Autowired
    private LopRepository lopRepository;

    @Autowired
    private SinhVienRepository sinhVienRepository;

    @Autowired
    public ModelMapper modelMapper;

    @Override
    public List<LopDTO> findAll() {
        List<Lop> lops = lopRepository.findAll();
        return lops.stream()
                .map((lop) -> this.modelMapper.map(lop, LopDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public LopDTO findLopById(Integer Id) {
        Lop lop = lopRepository.findById(Id).orElseThrow(() -> new NotFoundException("Lop", "Id", Id));
        return modelMapper.map(lop, LopDTO.class);
    }

    @Override
    public List<LopDTO> searchByTenLop(String tenLop) {
        return lopRepository.searchAllByTenLopContainingIgnoreCase(tenLop).stream()
                .map((lop) -> this.modelMapper.map(lop, LopDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<SinhVien> findAllSinhVienByLop(String maLop) {
        return sinhVienRepository.findByLop_MaLop(maLop);
    }
}
