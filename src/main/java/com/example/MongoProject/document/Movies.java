package com.example.MongoProject.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document
@Data
public class Movies {
    @Id
    private String id;
    private String director;
    private String title;
    private Double rating;

    private Map<String, String> muviesSettings = new HashMap<>();

    public Movies(){}
    public Movies(String director, String title, Double rating) {
        this.director = director;
        this.title = title;
        this.rating = rating;
    }
}
