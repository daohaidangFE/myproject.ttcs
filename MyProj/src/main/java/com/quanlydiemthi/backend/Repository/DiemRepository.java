package com.quanlydiemthi.backend.Repository;

import com.quanlydiemthi.backend.Entity.Diem;
import com.quanlydiemthi.backend.Entity.MonHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiemRepository extends JpaRepository<Diem, Integer> {
    @Query("SELECT d FROM Diem d WHERE d.sinhvien.maSV = :maSV")
    List<Diem> findByMaSV(String maSV);
    Diem findByid(Integer id);

}

