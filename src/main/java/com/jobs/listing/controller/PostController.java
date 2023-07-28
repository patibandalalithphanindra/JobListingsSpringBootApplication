package com.jobs.listing.controller;

import com.jobs.listing.model.Post;
import com.jobs.listing.repository.PostRepository;
import com.jobs.listing.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
public class PostController {
@Autowired
    PostRepository repo;
    @Autowired
    SearchRepository searchrepo;

    @GetMapping("/allPosts")
    public List<Post> getAllPosts()
    {
        return repo.findAll();
    }

    @GetMapping("/posts/{text}")
    public List<Post> search(@PathVariable String text)
    {
        return searchrepo.findByText(text);
    }

    @PostMapping("/post")
    public Post addPost(@RequestBody Post post)
    {
        return repo.save(post);
    }

    @DeleteMapping("/post/{_id}")
    public ResponseEntity<String> deletePost(@PathVariable String _id) {
        if (repo.existsById(_id)) {
            try {
                repo.deleteById(_id);
                return ResponseEntity.ok("Post deleted successfully.");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete post: " + e.getMessage());
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/postupdate/{_id}")
    public ResponseEntity<String> updatePost(@PathVariable String _id, @RequestBody Post updatedPost) {
        try {
            Optional<Post> optionalPost = repo.findById(_id);
            if (optionalPost.isPresent()) {
                Post existingPost = optionalPost.get();
                existingPost.setProfile(updatedPost.getProfile());
                existingPost.setDesc(updatedPost.getDesc());
                existingPost.setExp(updatedPost.getExp());
                existingPost.setTechs(updatedPost.getTechs());

                repo.save(existingPost);
                return ResponseEntity.ok("Post updated successfully.");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update post: " + e.getMessage());
        }
    }
}


