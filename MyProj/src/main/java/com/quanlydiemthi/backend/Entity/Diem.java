package com.quanlydiemthi.backend.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @JsonIgnoreProperties("diem")
    @ManyToOne
    @JoinColumn(name="maSV")
    private SinhVien sinhvien;

    @JsonIgnoreProperties("diem")
    @ManyToOne
    @JoinColumn(name="maMH")
    private MonHoc monhoc;

}
