package com.example.SpringRedisJWT_demo7.Service;

import com.example.SpringRedisJWT_demo7.Model.Personage;
import com.example.SpringRedisJWT_demo7.Repository.PersonageRepository;
import com.example.SpringRedisJWT_demo7.Security.PersonageDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PersonageService implements UserDetailsService {

    @Autowired
    private PersonageRepository operations;

    public void savePersonage(Personage personage){
        operations.save(personage);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Personage> findPersonage= Optional.ofNullable(operations.findByUsername(username));
        return new PersonageDetails(findPersonage.get());
    }
}
