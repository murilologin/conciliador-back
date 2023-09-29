package com.projeto.conciliador.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NativeScriptService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void executarSql(String sql){
        entityManager.createNativeQuery(sql).executeUpdate();
    }

    public Object executarSqlResultSingle(String sql) {
        return entityManager.createNativeQuery(sql).getSingleResult();
    }

    public List<?> executarSqlResultList(String sql, Object classe) {
        return entityManager.createNativeQuery(sql, (Class<?>) classe).getResultList();
    }

}
