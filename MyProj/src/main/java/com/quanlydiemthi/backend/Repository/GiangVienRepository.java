package com.quanlydiemthi.backend.Repository;

import com.quanlydiemthi.backend.Entity.GiangVien;
import com.quanlydiemthi.backend.Repository.Custom.GiangVienRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GiangVienRepository extends JpaRepository<GiangVien, Integer>, GiangVienRepositoryCustom {
    GiangVien findByMaGV(String maGV);
//    GiangVien findByUsername(String username);
}
