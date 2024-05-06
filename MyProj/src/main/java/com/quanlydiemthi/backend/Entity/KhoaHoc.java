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

public class KhoaHoc {
    @Id
    private Integer maKH;

    @Column(nullable = false)
    private String tenKH;

    @JsonIgnore
    @OneToMany(mappedBy = "khoahoc",fetch = FetchType.LAZY)
    private List<Lop> lop;
}
