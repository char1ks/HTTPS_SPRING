package com.example.SpringRedisJWT_demo7.Model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "translates")
public class Translate implements Serializable {
    private static final long serialVersionUID = 8433350764636919765L; // Уникальный идентификатор

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "start_words")
    private String start_words;

    @Column(name = "end_words")
    private String end_words;

    @Column(name = "start_language")
    private String start_language;

    @Column(name = "end_language")
    private String end_language;

    @ManyToOne
    @JoinColumn(name = "personage_id",referencedColumnName = "id")
    private Personage personage;

    @OneToMany(mappedBy = "translate")
    private List<Rate> rates;

    public Translate() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStart_words() {
        return start_words;
    }

    public void setStart_words(String start_words) {
        this.start_words = start_words;
    }

    public String getEnd_words() {
        return end_words;
    }

    public void setEnd_words(String end_words) {
        this.end_words = end_words;
    }

    public String getStart_language() {
        return start_language;
    }

    public void setStart_language(String start_language) {
        this.start_language = start_language;
    }

    public String getEnd_language() {
        return end_language;
    }

    public void setEnd_language(String end_language) {
        this.end_language = end_language;
    }

    public Personage getPersonage() {
        return personage;
    }

    public void setPersonage(Personage personage) {
        this.personage = personage;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }
}
