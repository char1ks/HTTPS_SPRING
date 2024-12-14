package com.example.SpringRedisJWT_demo7.Service;

import com.example.SpringRedisJWT_demo7.Model.Translate;
import com.example.SpringRedisJWT_demo7.Repository.TranslateRepository;
import com.example.SpringRedisJWT_demo7.Security.PersonageDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TranslateService {

    @Autowired
    private TranslateRepository operations;

    public List<Translate> getAllTranslates(){
        return operations.findAll();
    }
    @Cacheable(value = "translate", key = "#id")
    public Translate getConcreteTranslate(int id){
        return operations.findById(id).orElse(null);
    }
    @CachePut(value = "translate", key = "#result.id")
    public Translate saveTranslate(Translate translate){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        PersonageDetails details= (PersonageDetails) authentication.getPrincipal();
        translate.setPersonage(details.getPersonage());
        return operations.save(translate);
    }
    @CacheEvict(value = "translate", key = "#id")
    public void deleteTranslate(int id){
        operations.deleteById(id);
    }
}
