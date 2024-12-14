package com.example.SpringRedisJWT_demo7.Model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "rate")
public class Rate implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ai_answer")
    private String ai_answer;

    @ManyToOne
    @JoinColumn(name = "translate_id", referencedColumnName = "id")
    private Translate translate;

    public Rate() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAi_answer() {
        return ai_answer;
    }

    public void setAi_answer(String ai_answer) {
        this.ai_answer = ai_answer;
    }

    public Translate getTranslate() {
        return translate;
    }

    public void setTranslate(Translate translate) {
        this.translate = translate;
    }
}
