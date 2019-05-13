package com.zversal.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.zversal.api.model.Users;

@Repository
public interface UserRepository extends MongoRepository<Users, Long> {
	Users findByUsername(String username);
}
