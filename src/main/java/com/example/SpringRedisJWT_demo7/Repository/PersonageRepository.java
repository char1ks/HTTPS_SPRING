package com.example.SpringRedisJWT_demo7.Repository;

import com.example.SpringRedisJWT_demo7.Model.Personage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonageRepository extends JpaRepository<Personage,Integer> {
    Personage findByUsername(String username);
}
