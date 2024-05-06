package com.quanlydiemthi.backend.Repository;

import com.quanlydiemthi.backend.Entity.Khoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KhoaRepository extends JpaRepository<Khoa, Integer> {
    List<Khoa> searchAllByTenKContainingIgnoreCase(String tenK);
}
