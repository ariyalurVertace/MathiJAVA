package com.vertace.javapostgre.repository;

import org.springframework.data.jpa.repository.Query;
import com.vertace.javapostgre.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    Favorite findByName(String name);
}