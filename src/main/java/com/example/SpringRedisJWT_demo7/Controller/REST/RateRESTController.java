package com.example.SpringRedisJWT_demo7.Controller.REST;

import com.example.SpringRedisJWT_demo7.Model.Rate;
import com.example.SpringRedisJWT_demo7.Model.Translate;
import com.example.SpringRedisJWT_demo7.Service.LLMControl;
import com.example.SpringRedisJWT_demo7.Service.RateService;
import com.example.SpringRedisJWT_demo7.Service.TranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rate/api")
public class RateRESTController {

    @Autowired
    private RateService operations;

    @Autowired
    private TranslateService operationsTranslate;

    @Autowired
    private LLMControl llmControl;

    @GetMapping("/all")
    public List<Rate> allRates(){
        return operations.getAllRates();
    }
    @GetMapping("/concrete/{rate_id}")
    public Rate getConcreteRate(@PathVariable("rate_id")int id){
        return operations.getConcreteRate(id);
    }
    @PostMapping("/save/{translate_id}")
    public ResponseEntity<HttpStatus> saveRate(@PathVariable("translate_id")int translate_id){
        Translate translate=operationsTranslate.getConcreteTranslate(translate_id);
        String answer=llmControl.generate("Привет,твоя задача оценить перевод(дать оценку ИСКЛЮЧИТЕЛЛЬНО НА РУССКОМ ЯЗЫКУ),я предоставлю начальную фразу " +
                "и ее перевод на определенный язык:"+translate.getStart_words()+"-начальная фраза.Перевод-"+translate.getEnd_words());

        //Установка полей
        Rate rate=new Rate();
        rate.setAi_answer(answer);
        rate.setTranslate(translate);
        //---------------

        operations.saveRate(rate);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping("/delete/{rate_id}")
    public ResponseEntity<HttpStatus> deleteRate(@PathVariable("rate_id")int rate_id){
        operations.deleteRate(rate_id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
