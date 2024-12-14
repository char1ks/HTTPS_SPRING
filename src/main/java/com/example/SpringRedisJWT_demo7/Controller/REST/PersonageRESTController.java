package com.example.SpringRedisJWT_demo7.Controller.REST;

import com.example.SpringRedisJWT_demo7.Model.Personage;
import com.example.SpringRedisJWT_demo7.Security.JWTUtil;
import com.example.SpringRedisJWT_demo7.Service.PersonageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/personage/api")
public class PersonageRESTController {
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private PersonageService operations;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginClient(@RequestBody Personage author) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(author.getUsername(), author.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(auth);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("Authentication successful for user: " + author.getUsername());
        } catch (Exception exc) {
            System.out.println("Authentication failed: " + exc.getMessage());
            throw new RuntimeException(exc);
        }
        String token = jwtUtil.generateToken(author.getUsername());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        return new ResponseEntity<>(Map.of("Token", token), headers, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Map<String, String>> regClient(@RequestBody Personage author) {
        System.out.println("Регистрация пользователя: " + author.getUsername());
        operations.savePersonage(author);
        String token = jwtUtil.generateToken(author.getUsername());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        return new ResponseEntity<>(Map.of("Token", token), headers, HttpStatus.OK);
    }

}
