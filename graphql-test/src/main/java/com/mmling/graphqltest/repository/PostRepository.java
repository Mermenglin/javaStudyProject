package com.mmling.graphqltest.repository;

import com.mmling.graphqltest.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Meimengling
 * @date 2022-4-7 15:01
 */
@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    @Query("{ 'title': ?0 }")
    Optional<Post> findByTitle(String title);
}
