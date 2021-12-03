package com.vertace.javapostgre.repository;
import org.springframework.data.jpa.repository.Query;
import com.vertace.javapostgre.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product finindByName(String name);
}
