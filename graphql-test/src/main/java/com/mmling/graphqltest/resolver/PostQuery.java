package com.mmling.graphqltest.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mmling.graphqltest.entity.Post;
import com.mmling.graphqltest.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author Meimengling
 * @date 2022-4-7 15:02
 */
@Component
public class PostQuery implements GraphQLQueryResolver {

    @Autowired
    private PostRepository postRepository;

    public List<Post> posts() {
        return postRepository.findAll();
    }

    public Optional<Post> post(String id) {
        return postRepository.findById(id);
    }

}
