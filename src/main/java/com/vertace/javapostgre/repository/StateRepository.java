package com.vertace.javapostgre.repository;

import org.springframework.data.jpa.repository.Query;
import com.vertace.javapostgre.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface StateRepository extends JpaRepository<State, Long> {

    State findByName(String name);
    @Query(value = "SELECT s.id, s.name from state s where s.state = ?1",nativeQuery = true)
    State getStateIdAndName(Long state);
}