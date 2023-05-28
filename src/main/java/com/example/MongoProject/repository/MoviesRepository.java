package com.example.MongoProject.repository;

import com.example.MongoProject.document.Movies;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesRepository extends MongoRepository<Movies, Object> {
}
