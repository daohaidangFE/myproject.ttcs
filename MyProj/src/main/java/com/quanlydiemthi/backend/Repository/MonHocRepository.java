package com.quanlydiemthi.backend.Repository;

import com.quanlydiemthi.backend.Entity.MonHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MonHocRepository extends JpaRepository<MonHoc, Integer> {
    List<MonHoc> findAllByTenMHContainingIgnoreCase(String tenMH);
    MonHoc findByMaMH(String maMH);
}
