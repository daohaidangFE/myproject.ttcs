package com.quanlydiemthi.backend.Repository.Custom.Impl;

import com.quanlydiemthi.backend.Entity.GiangVien;
import com.quanlydiemthi.backend.Repository.Custom.GiangVienRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Map;

public class GiangVienRepositoryImpl implements GiangVienRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    public static void queryNormal(StringBuilder sql, Map<String, String> conditions) {
        try {
            for (Map.Entry<String, String> entry : conditions.entrySet()) {
                String fieldName = entry.getKey();
                String value = entry.getValue().replaceAll("\s\s+", " ").trim();
                if ("magv".equals(fieldName) || "username".equals(fieldName) || "gioi_tinh".equals(fieldName) || "tengv".equals(fieldName)) {
                    if (value != null && !value.isEmpty()) {
                        sql.append("AND gv.").append(fieldName).append(" LIKE '%").append(value).append("%' ");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<GiangVien> findTeachers(Map<String, String> conditions) {
        StringBuilder sql = new StringBuilder("SELECT gv.* FROM giang_vien gv ");
        sql.append("WHERE 1 = 1 AND gv.is_active = 1 ");
        queryNormal(sql, conditions);
        sql.append(" GROUP BY gv.magv");
        Query query = entityManager.createNativeQuery(sql.toString(), GiangVien.class);
        return query.getResultList();
    }
}
