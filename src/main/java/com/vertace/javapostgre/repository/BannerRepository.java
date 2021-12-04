package com.vertace.javapostgre.repository;
import org.springframework.data.jpa.repository.Query;
import com.vertace.javapostgre.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {

    Banner findByName(String name);
}