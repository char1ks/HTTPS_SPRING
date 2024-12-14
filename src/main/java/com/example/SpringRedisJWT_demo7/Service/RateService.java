package com.example.SpringRedisJWT_demo7.Service;

import com.example.SpringRedisJWT_demo7.Model.Rate;
import com.example.SpringRedisJWT_demo7.Repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RateService {

    @Autowired
    private RateRepository operations;

    public List<Rate> getAllRates(){
        return operations.findAll();
    }
    @Cacheable(value = "rate", key = "#id")
    public Rate getConcreteRate(int id){
        return operations.findById(id).orElse(null);
    }
    @CachePut(value = "rate", key = "#result.id")
    public Rate saveRate(Rate rate){
        return operations.save(rate);
    }
    @CacheEvict(value = "rate", key = "#id")
    public void deleteRate(int id){
        operations.deleteById(id);
    }
}
