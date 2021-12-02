package com.vertace.javapostgre.repository;

import org.springframework.data.jpa.repository.Query;
import com.vertace.javapostgre.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findByName(String name);
}