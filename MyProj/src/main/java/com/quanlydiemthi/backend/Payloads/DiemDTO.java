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

    private Float diemGiuaKy;

    private Float diemCuoiKy;

    private String hocKy;

    private String maMH;

    private String maSV;
}