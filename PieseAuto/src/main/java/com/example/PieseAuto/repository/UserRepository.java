package com.example.PieseAuto.repository;

import com.example.PieseAuto.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findFirstByUserNameAndPassword(String userName, String password);
}
