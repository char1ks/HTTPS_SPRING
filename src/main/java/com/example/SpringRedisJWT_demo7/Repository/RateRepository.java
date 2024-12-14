package com.example.SpringRedisJWT_demo7.Repository;

import com.example.SpringRedisJWT_demo7.Model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<Rate,Integer> {

}
