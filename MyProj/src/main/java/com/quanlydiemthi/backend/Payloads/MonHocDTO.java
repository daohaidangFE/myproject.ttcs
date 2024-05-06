package com.quanlydiemthi.backend.Payloads;


import com.quanlydiemthi.backend.Entity.Diem;
import com.quanlydiemthi.backend.Entity.GiangVien;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonHocDTO {

    private String maMH;

    private String tenMH;

    private Integer soTinChi;

    private List<Diem> diem;

    private GiangVien giangvien;
}
