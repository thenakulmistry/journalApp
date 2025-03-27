package com.nakul.firstSpringProject.repositories;

import com.nakul.firstSpringProject.entity.JournalEntry;
import com.nakul.firstSpringProject.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUsername(String username);
    void deleteByUsername(String username);
}
