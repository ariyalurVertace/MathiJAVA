package com.vertace.javapostgre.repository;
import org.springframework.data.jpa.repository.Query;
import com.vertace.javapostgre.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByName(String name);
}