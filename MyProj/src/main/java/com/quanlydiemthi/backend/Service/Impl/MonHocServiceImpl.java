package com.quanlydiemthi.backend.Service.Impl;

import com.quanlydiemthi.backend.Entity.MonHoc;
import com.quanlydiemthi.backend.Exceptions.NotFoundException;
import com.quanlydiemthi.backend.Payloads.MonHocDTO;
import com.quanlydiemthi.backend.Repository.MonHocRepository;
import com.quanlydiemthi.backend.Service.IMonHocService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonHocServiceImpl implements IMonHocService {

    @Autowired
    private MonHocRepository monHocRepository;

    @Autowired
    public ModelMapper modelMapper;

    @Override
    public List<MonHocDTO> findAll() {
        List<MonHoc> monHocList = monHocRepository.findAll();
        return monHocList.stream()
                .map((monhoc) -> this.modelMapper.map(monhoc, MonHocDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MonHocDTO findMonHocById(Integer Id) {
        MonHoc monHocList = monHocRepository.findById(Id).orElseThrow(() -> new NotFoundException("mon hoc", "Id", Id));
        return this.modelMapper.map(monHocList, MonHocDTO.class);
    }

    @Override
    public List<MonHocDTO> searchMonHocByTenMH(String tenMH) {
        List<MonHoc> monHocList = monHocRepository.findAllByTenMHContainingIgnoreCase(tenMH);
        return monHocList.stream()
                .map((monhoc) -> this.modelMapper.map(monhoc, MonHocDTO.class))
                .collect(Collectors.toList());
    }
}
