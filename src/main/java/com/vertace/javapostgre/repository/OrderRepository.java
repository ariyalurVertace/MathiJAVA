package com.vertace.javapostgre.repository;

import org.springframework.data.jpa.repository.Query;
import com.vertace.javapostgre.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByName(String name);
}