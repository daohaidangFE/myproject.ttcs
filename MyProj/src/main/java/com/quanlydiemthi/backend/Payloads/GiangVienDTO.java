package com.quanlydiemthi.backend.Payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.quanlydiemthi.backend.Entity.Role;
import jakarta.persistence.Column;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class GiangVienDTO {
    private String maGV;

    private String tenGV;

    private String username;

    private boolean isActive;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String gioiTinh;

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Role role;

    private String newPassword;
}
