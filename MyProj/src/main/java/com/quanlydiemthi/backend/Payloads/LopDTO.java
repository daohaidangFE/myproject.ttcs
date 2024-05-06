package com.quanlydiemthi.backend.Payloads;


import com.quanlydiemthi.backend.Entity.Khoa;
import com.quanlydiemthi.backend.Entity.KhoaHoc;
import com.quanlydiemthi.backend.Entity.SinhVien;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LopDTO {

    private String maLop;

    private String tenLop;

    private List<SinhVien> sinhvien;

    private KhoaHoc khoahoc;

    private Khoa khoa;

}
