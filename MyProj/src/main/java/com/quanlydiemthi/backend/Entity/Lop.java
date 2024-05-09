package com.quanlydiemthi.backend.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"sinhvien", "khoahoc", "khoa", "giangVien"})
public class Lop {
    @Id
    private String maLop;

    @Column(nullable = false)
    private String tenLop;

    @JsonIgnore
    @OneToMany(mappedBy = "lop",fetch = FetchType.LAZY)
    private List<SinhVien> sinhvien;

    @ManyToOne
    @JoinColumn(name = "maKH")
    private KhoaHoc khoahoc;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "maK")
    private Khoa khoa;

    @OneToOne
    @JoinColumn(name = "maGV")
    private GiangVien giangVien;

}
