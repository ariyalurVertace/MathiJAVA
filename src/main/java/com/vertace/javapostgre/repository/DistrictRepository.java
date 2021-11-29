package com.vertace.javapostgre.repository;
import org.springframework.data.jpa.repository.Query;
import com.vertace.javapostgre.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

    District findByName(String name);
}