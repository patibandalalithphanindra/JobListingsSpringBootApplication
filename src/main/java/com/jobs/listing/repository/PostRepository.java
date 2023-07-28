package com.jobs.listing.repository;

import com.jobs.listing.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends MongoRepository<Post,String>, CrudRepository<Post,String>
{

}
