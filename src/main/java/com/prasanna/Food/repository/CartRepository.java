package com.prasanna.Food.repository;

import com.prasanna.Food.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}