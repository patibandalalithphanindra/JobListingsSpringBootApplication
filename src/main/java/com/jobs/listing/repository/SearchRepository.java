package com.jobs.listing.repository;

import com.jobs.listing.model.Post;

import java.util.List;

public interface SearchRepository {
    List<Post> findByText(String text);
}
