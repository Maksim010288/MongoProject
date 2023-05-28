package com.example.MongoProject.controller;

import com.example.MongoProject.document.Movies;
import com.example.MongoProject.kafka.producer.KafkaProducerConfig;
import com.example.MongoProject.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class MoviesController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    @Autowired
    private MovieService movieService;
    @Autowired
    private KafkaProducerConfig config;

    public MoviesController(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Movies muvies) {
        LOG.info("create new Muvies " + muvies);
        config.sendMessage(muvies);
        return ResponseEntity.ok("create new object");
    }

    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public List<Movies> findAll() {
        LOG.info("findAll muvies..");
        return movieService.findAll();
    }

    @RequestMapping(value = "/findBy/{name}", method = RequestMethod.GET)
    public List<Movies> fingByDirector(@PathVariable String name) {
        return movieService.findByDirector(name);
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
    public void updateMuvies(@PathVariable Object id, @RequestBody Movies muvies) {
        movieService.update(id, muvies);
        ResponseEntity.ok().body("Update date id : " + id);
        LOG.info("update date id : " + id);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    public void deleteMuvies(@PathVariable Object id) {
        movieService.delete(id);
        ResponseEntity.ok().body("delete date id : " + id);
        LOG.info("delete date id : " + id);
    }
}
