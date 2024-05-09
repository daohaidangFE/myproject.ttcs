package com.quanlydiemthi.backend.Repository.Custom.Impl;

import com.quanlydiemthi.backend.Entity.SinhVien;
import com.quanlydiemthi.backend.Repository.Custom.SinhVienRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Map;

public class SinhVienRepositoryImpl implements SinhVienRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    private void joinTable(Map<String, String> conditions, StringBuilder sql) {
        String maGV = conditions.get("maGV");
        if(maGV != null && !maGV.isEmpty()) {
            sql.append(" JOIN lop l ON sv.ma_lop = l.ma_lop ");
            sql.append(" JOIN giang_vien gv ON l.maGV = gv.maGV ");
        }
    }
    public static void querySpecial(StringBuilder sql, Map<String, String> conditions) {
        String maGV = conditions.get("maGV");
        if (maGV != null && !maGV.isEmpty()) {
            sql.append("AND gv.maGV" + " = '" + maGV + "' ");
        }
    }
    public static void queryNormal(StringBuilder sql, Map<String, String> conditions) {
        try {
            for (Map.Entry<String, String> entry : conditions.entrySet()) {
                String fieldName = entry.getKey();
                String value = entry.getValue().replaceAll("\s\s+", " ").trim();
                if ("masv".equals(fieldName) || "username".equals(fieldName) || "gioi_tinh".equals(fieldName) || "tensv".equals(fieldName) || "is_active".equals(fieldName)) {
                    if (value != null && !value.isEmpty()) {
                        sql.append("AND sv.").append(fieldName).append(" LIKE '%").append(value).append("%' ");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<SinhVien> findStudents(Map<String, String> conditions) {
        StringBuilder sql = new StringBuilder("SELECT sv.* FROM sinh_vien sv ");
        joinTable(conditions, sql);
        sql.append("WHERE 1 = 1 AND sv.is_active = 1 ");
        queryNormal(sql, conditions);
        querySpecial(sql, conditions);
        sql.append(" GROUP BY sv.masv");
        Query query = entityManager.createNativeQuery(sql.toString(), SinhVien.class);
        return query.getResultList();
    }
}
