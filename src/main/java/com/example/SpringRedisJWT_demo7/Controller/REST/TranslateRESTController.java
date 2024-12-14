package com.example.SpringRedisJWT_demo7.Controller.REST;
import com.example.SpringRedisJWT_demo7.Model.Translate;
import com.example.SpringRedisJWT_demo7.Service.TranslateService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/translate/api")
public class TranslateRESTController {
    @Autowired
    private TranslateService operations;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/all")
    public List<Translate> allTranslates() {
        return operations.getAllTranslates();
    }

    @GetMapping("/concrete/{id}")
    public Translate concreteTranslate(@PathVariable("id") int id) {
        return operations.getConcreteTranslate(id);
    }

    @PostMapping("/save")
    public ResponseEntity<HttpStatus> saveTranslate(@RequestBody Translate t) {
        try {
            // Создаем OkHttpClient
            OkHttpClient client = new OkHttpClient();
            // Формируем JSON-объект для запроса
            MediaType mediaType = MediaType.parse("application/json");
            String jsonBody = String.format(
                    "{\"q\":\"%s\",\"source\":\"%s\",\"target\":\"%s\"}",
                    t.getStart_words(),
                    t.getStart_language(),
                    t.getEnd_language()
            );

            okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, jsonBody);
            Request request = new Request.Builder()
                    .url("https://deep-translate1.p.rapidapi.com/language/translate/v2")
                    .post(body)
                    .addHeader("x-rapidapi-key", "bd9d960bf9mshd30c2d87659a793p1fb9b6jsn078d41efcb71")
                    .addHeader("x-rapidapi-host", "deep-translate1.p.rapidapi.com")
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                JsonNode jsonResponse = objectMapper.readTree(responseBody);
                String translatedText = jsonResponse
                        .get("data")
                        .get("translations")
                        .get("translatedText")
                        .asText();
                t.setEnd_words(translatedText);
                operations.saveTranslate(t);
                return ResponseEntity.ok(HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteTranslate(@PathVariable("id") int id) {
        operations.deleteTranslate(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}