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

public class GiangVien {
    @Id
    private String maGV;

    private String tenGV;

    @Column(nullable = false)
    private String username;

    private boolean isActive = true;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String gioiTinh;

    @Column(nullable = false)
    private String email;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne(mappedBy = "giangVien")
    private Lop lop;
}
