package com.quanlydiemthi.backend.Service.Impl;


import com.quanlydiemthi.backend.Entity.Diem;
import com.quanlydiemthi.backend.Entity.MonHoc;
import com.quanlydiemthi.backend.Entity.SinhVien;
import com.quanlydiemthi.backend.Payloads.DiemDTO;
import com.quanlydiemthi.backend.Payloads.SinhVienDTO;
import com.quanlydiemthi.backend.Repository.DiemRepository;
import com.quanlydiemthi.backend.Repository.MonHocRepository;
import com.quanlydiemthi.backend.Repository.SinhVienRepository;
import com.quanlydiemthi.backend.Service.IDiemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DiemServiceImpl implements IDiemService {
    @Autowired
    private DiemRepository diemRepository;
    @Autowired
    private MonHocRepository monHocRepository;
    @Autowired
    private SinhVienRepository sinhVienRepository;

    @Autowired
    public ModelMapper modelMapper;

    @Override
    public List<DiemDTO> findAll(String maSV) {
        List<Diem> diems = diemRepository.findByMaSV(maSV);
        List<DiemDTO> diemDTOList = new ArrayList<>();
        for(Diem diem : diems) {
            DiemDTO dto = new DiemDTO();
            int soTinChi = diem.getMonhoc().getSoTinChi();
            float Dtb = (float) (diem.getDiemGiuaKy()*0.3 + diem.getDiemCuoiKy()*0.7);
            String diemHeChu = getString(Dtb);
            float he4 = getHe4(Dtb);
            dto.setId(diem.getId());
            dto.setMaMH(diem.getMonhoc().getMaMH());
            dto.setDiemHe4(he4);
            dto.setTenHocPhan(diem.getMonhoc().getTenMH());
            dto.setSoTinChi(soTinChi);
            dto.setDiemChu(diemHeChu);
            dto.setDiemTongKet(Dtb);
            diemDTOList.add(dto);
        }
        return diemDTOList;
    }

    public static String getString(float dtb){
        String diemHeChu = "";
        if(dtb < 4){
            diemHeChu = "F";
        }
        else if(dtb >= 4 && dtb < 5.5){
            diemHeChu = "D";
        }
        else if(dtb >= 5.5 && dtb < 6){
            diemHeChu = "D+";
        }
        else if(dtb >= 6 && dtb < 6.5){
            diemHeChu = "C";
        }
        else if(dtb >= 6.5 && dtb < 7){
            diemHeChu = "C+";
        }
        else if(dtb >= 7 && dtb < 8){
            diemHeChu = "B";
        }
        else if(dtb >= 8 && dtb < 8.5){
            diemHeChu = "B+";
        }
        else if(dtb >= 8.5 && dtb < 9){
            diemHeChu = "A";
        }
        else if(dtb >= 9 && dtb <= 10){
            diemHeChu = "A+";
        }
        return diemHeChu;
    }

    public static float getHe4(float dtb){
        float he4 = 0;
        if(dtb < 4){
            he4 = 0.0f;
        }
        else if(dtb >= 4 && dtb < 5.5){
            he4 = 1.0f;
        }
        else if(dtb >= 5.5 && dtb < 6){
            he4 = 1.5f;
        }
        else if(dtb >= 6 && dtb < 6.5){
            he4 = 2.0f;
        }
        else if(dtb >= 6.5 && dtb < 7){
            he4 = 2.5f;
        }
        else if(dtb >= 7 && dtb < 8){
            he4 = 3.0f;
        }
        else if(dtb >= 8 && dtb < 8.5){
            he4 = 3.5f;
        }
        else if(dtb >= 8.5 && dtb < 9){
            he4 = 3.7f;
        }
        else if(dtb >= 9 && dtb <= 10){
            he4 = 4.0f;
        }
        return he4;
    }

    @Override
    public List<DiemDTO> findByMaSV(String maSV) {
        return null;
    }
    @Override
    public DiemDTO findDiem(Integer id) {
        Diem diem = diemRepository.findByid(id);
        DiemDTO dto = new DiemDTO();
        dto.setId(diem.getId());
        dto.setDiemCuoiKy(diem.getDiemCuoiKy());
        dto.setDiemGiuaKy(diem.getDiemGiuaKy());
        dto.setTenHocPhan(diem.getMonhoc().getTenMH());
        dto.setHocKy(diem.getHocKy());
        dto.setMaMH(diem.getMonhoc().getMaMH());
        dto.setMaSV(diem.getSinhvien().getMaSV());
        return dto;
    }

    @Override
    public void createDiem(DiemDTO diemDTO) {
        Diem diem = new Diem();
        diem.setId(diemDTO.getId());
        diem.setDiemCuoiKy(diemDTO.getDiemCuoiKy());
        diem.setDiemGiuaKy(diemDTO.getDiemGiuaKy());
        diem.setHocKy(diemDTO.getHocKy());
        MonHoc monHoc = monHocRepository.findByMaMH(diemDTO.getMaMH());
        diem.setMonhoc(monHoc);
        SinhVien sinhVien = sinhVienRepository.findByMaSV(diemDTO.getMaSV());
        diem.setSinhvien(sinhVien);
        diemRepository.save(diem);
    }

    @Override
    public void updateDiem(DiemDTO diemDTO) {
        Diem diem = diemRepository.findByid(diemDTO.getId());
        diem.setDiemCuoiKy(diemDTO.getDiemCuoiKy());
        diem.setDiemGiuaKy(diemDTO.getDiemGiuaKy());
        diem.setHocKy(diemDTO.getHocKy());
        MonHoc monHoc = monHocRepository.findByMaMH(diemDTO.getMaMH());
        diem.setMonhoc(monHoc);
        SinhVien sinhVien = sinhVienRepository.findByMaSV(diemDTO.getMaSV());
        diem.setSinhvien(sinhVien);
        diemRepository.save(diem);
    }

    @Override
    public void deleteDiem(Integer Id) {
        diemRepository.deleteById(Id);
    }
}