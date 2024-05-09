package com.quanlydiemthi.backend.Payloads;

import com.quanlydiemthi.backend.Entity.MonHoc;
import com.quanlydiemthi.backend.Entity.SinhVien;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class DiemDTO {
    private Integer id;

    private String tenHocPhan;

    private Float diemTongKet;

    private Float diemHe4;
    private String diemChu;
    private int soTinChi;
}
