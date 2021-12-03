
package com.vertace.javapostgre.repository;
import org.springframework.data.jpa.repository.Query;
import com.vertace.javapostgre.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);
}
