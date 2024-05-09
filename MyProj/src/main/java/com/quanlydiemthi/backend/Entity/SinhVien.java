package com.quanlydiemthi.backend.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"diem", "lop"})
public class SinhVien {
    @Id
    private String maSV;

    private String tenSV;

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
    @OneToMany(mappedBy = "sinhvien",fetch = FetchType.LAZY)
    private List<Diem> diem;

    @ManyToOne
    @JoinColumn(name = "maLop")
    private Lop lop;
}
