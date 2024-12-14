package com.example.SpringRedisJWT_demo7.Repository;

import com.example.SpringRedisJWT_demo7.Model.Translate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslateRepository extends JpaRepository<Translate,Integer> {
}
