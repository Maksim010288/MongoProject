package com.example.MongoProject.service;

import com.example.MongoProject.document.Movies;
import com.example.MongoProject.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
    @Autowired
    private MoviesRepository moviesRepository;

    public Movies create(Movies muvies) {
        return moviesRepository.save(muvies);
    }

    public List<Movies> findAll() {
        return new ArrayList<>(moviesRepository.findAll());
    }

    public void update(Object id, Movies movies) {
        if (moviesRepository.existsById(id)) {
            moviesRepository.deleteById(id);
            moviesRepository.save(movies);
        }
    }

    public List<Movies> findByDirector(String name) {
        return moviesRepository.findAll().stream()
                .filter(muvies -> muvies.getDirector().equals(name))
                .collect(Collectors.toList());
    }

    public List<Movies> findByTitle(String name) {
        return moviesRepository.findAll().stream()
                .filter(muvies -> muvies.getTitle().equals(name))
                .collect(Collectors.toList());
    }

    public List<Movies> findByRating(Double name) {
        return moviesRepository.findAll().stream()
                .filter(muvies -> muvies.getRating().equals(name))
                .collect(Collectors.toList());
    }

    public void delete(Object id) {
        moviesRepository.deleteById(id);
    }

}
