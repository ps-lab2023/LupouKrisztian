package com.example.PieseAuto.repository;

import com.example.PieseAuto.model.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
    List<Cart> findAllByUser_UserID(Long userID);
}
