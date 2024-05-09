package com.quanlydiemthi.backend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class MonHoc {
    @Id
    private String maMH;

    @Column(nullable = false)
    private String tenMH;

    @Column(nullable = false)
    private Integer soTinChi;

    @JsonIgnore
    @OneToMany(mappedBy = "monhoc",fetch = FetchType.LAZY)
        private List<Diem> diem;

}
