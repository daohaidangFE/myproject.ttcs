package com.quanlydiemthi.backend.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Diem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String hocKy;

    @Column(nullable = false)
    private Float diemGiuaKy;

    @Column(nullable = false)
    private Float diemCuoiKy;

    @Column(nullable = false)
    private Float diemTongKet;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="maSV")
    private SinhVien sinhvien;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="maMH")
    private MonHoc monhoc;
}
