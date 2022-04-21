package com.mmling.graphqltest.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.mmling.graphqltest.entity.Post;
import com.mmling.graphqltest.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Meimengling
 * @date 2022-4-7 15:02
 */
@Component
public class PostMutation implements GraphQLMutationResolver {

    @Autowired
    private PostRepository postRepository;

    /**
     * Create Post
     *
     * @param post The create Post entity
     * @return The created Post entity
     */
    public Post createPost(Post post) {
        Post newPost = Post.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .build();

        return postRepository.save(newPost);
    }

    /**
     * Update Post
     *
     * @param id   The update post id
     * @param post The update post entity
     * @return Had updated post entity
     * @throws Exception Throw exception when the entity is not found by the given id
     */
    public Post updatePost(String id, Post post) throws Exception {
        Post currentPost = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post " + id + " Not Exist"));

        currentPost.setTitle(post.getTitle());
        currentPost.setContent(post.getContent());

        return postRepository.save(currentPost);
    }

    /**
     * Update Post by title
     *
     * @param title   The update post title
     * @param post The update post entity
     * @return Had updated post entity
     * @throws Exception Throw exception when the entity is not found by the given id
     */
    public Post updatePostTitle(String title, Post post) throws Exception {
        Post currentPost = postRepository.findByTitle(title)
                .orElseThrow(() -> new IllegalArgumentException("Post " + title + " Not Exist"));

        currentPost.setTitle(post.getTitle());
        currentPost.setContent(post.getContent());

        return postRepository.save(currentPost);
    }

    /**
     * Delete Post
     *
     * @param id The delete Post id
     * @return The deleted Post's id
     * @throws Exception Throw exception when the entity is not found by the given id
     */
    public String deletePost(String id) throws Exception {
        postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post " + id + " Not Exist"));

        postRepository.deleteById(id);
        return id;
    }
}