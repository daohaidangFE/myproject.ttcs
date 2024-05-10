package com.quanlydiemthi.backend.Payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.quanlydiemthi.backend.Entity.Diem;
import com.quanlydiemthi.backend.Entity.Lop;
import com.quanlydiemthi.backend.Entity.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"diem", "lop"})
public class SinhVienDTO {

    private String maSV;

    private String tenSV;

    private boolean isActive;

    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String gioiTinh;

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Role role;

    private String tenLop;

    private Lop lop;

    private String maLop;

    private Diem diem;
}