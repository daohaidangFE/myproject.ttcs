package com.quanlydiemthi.backend.Repository;

import com.quanlydiemthi.backend.Entity.SinhVien;
import com.quanlydiemthi.backend.Repository.Custom.SinhVienRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SinhVienRepository extends JpaRepository<SinhVien, Integer>, SinhVienRepositoryCustom {
    SinhVien findByMaSV(String maSV);
    List<SinhVien> findByLop_MaLop(String maLop);
}
