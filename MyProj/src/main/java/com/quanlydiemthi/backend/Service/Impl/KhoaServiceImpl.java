package com.quanlydiemthi.backend.Service.Impl;

import com.quanlydiemthi.backend.Entity.Khoa;
import com.quanlydiemthi.backend.Exceptions.NotFoundException;
import com.quanlydiemthi.backend.Payloads.KhoaDTO;
import com.quanlydiemthi.backend.Repository.KhoaRepository;
import com.quanlydiemthi.backend.Service.IKhoaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class KhoaServiceImpl implements IKhoaService {
    @Autowired
    private KhoaRepository khoaRepository;

    @Autowired
    public ModelMapper modelMapper;

    @Override
    public List<KhoaDTO> findAll() {
        List<Khoa> khoaList = khoaRepository.findAll();
        return khoaList.stream()
                .map((khoa) ->{
                    KhoaDTO khoaDTO = modelMapper.map(khoa, KhoaDTO.class);
                    String str = "";
                    for (int i = 0;i < khoaDTO.getLop().size() ;i++){
                        str = str + khoaDTO.getLop().get(i).getTenLop();
                        if(i < khoaDTO.getLop().size() - 1){
                            str = str + ", ";
                        }
                    }
                    if(str == null || str.equals("")) {
                        str ="Chưa có lớp";
                    }
                    khoaDTO.setDsLop(str);
                    return khoaDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public KhoaDTO findKhoaById(Integer Id) {
        Khoa khoa = khoaRepository.findById(Id).orElseThrow(() -> new NotFoundException("khoa", "Id", Id));
        return this.modelMapper.map(khoa, KhoaDTO.class);
    }

    @Override
    public List<KhoaDTO> findByTenKhoa(String tenK) {
        List<Khoa> khoaList = khoaRepository.searchAllByTenKContainingIgnoreCase(tenK);
        return khoaList.stream()
                .map((khoa) -> this.modelMapper.map(khoa, KhoaDTO.class))
                .collect(Collectors.toList());
    }
}
