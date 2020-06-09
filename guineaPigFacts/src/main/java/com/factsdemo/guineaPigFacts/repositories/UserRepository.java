package com.factsdemo.guineaPigFacts.repositories;
import com.factsdemo.guineaPigFacts.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
public class UserRepository extends MongoRepository<User, String> {
    public User findByUserName(String userName);
}
