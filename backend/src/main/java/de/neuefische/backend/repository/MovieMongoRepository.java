package de.neuefische.backend.repository;

import de.neuefische.backend.model.Movie;
import de.neuefische.backend.model.MovieDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieMongoRepository extends MongoRepository<MovieDTO, String> {
}
