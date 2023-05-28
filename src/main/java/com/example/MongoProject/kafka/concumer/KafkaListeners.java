package com.example.MongoProject.kafka.concumer;

import com.example.MongoProject.document.Movies;
import com.example.MongoProject.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class KafkaListeners {
    private Logger logger = LoggerFactory.getLogger(KafkaListeners.class);
    @Autowired
    private MovieService movieService;

    @KafkaListener(topics = "testRoom", groupId = "foo")
    void listener(String data) {
        String substring = substring(data);
        Movies movies = new Movies(
                slitByPoint(substring).get(1),
                slitByPoint(substring).get(3),
                Double.parseDouble(slitByPoint(substring).get(5)));
        logger.info("Create new object : " + movies + " in mongoDB");
        movieService.create(movies);

    }

    private String substring(String data) {
        return data.substring(16, data.length() - 3);
    }

    private List<String> slitByPoint(String data) {
        List<String> list = new ArrayList<>();
        for (String s : data.split(",")) {
            list.addAll(Arrays.asList(splitByEqual(s)));
        }
        return list;
    }

    private String[] splitByEqual(String data) {
        return data.split("=");
    }
}
