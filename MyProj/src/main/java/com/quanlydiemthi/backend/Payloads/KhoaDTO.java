package com.quanlydiemthi.backend.Payloads;

import com.quanlydiemthi.backend.Entity.Lop;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class KhoaDTO {

    private Integer maK;

    private String tenK;

    private List<Lop> lop;
}
