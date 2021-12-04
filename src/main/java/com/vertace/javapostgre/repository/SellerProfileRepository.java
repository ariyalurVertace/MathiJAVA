package com.vertace.javapostgre.repository;

import com.vertace.javapostgre.entity.SellerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerProfileRepository extends JpaRepository<SellerProfile, Long> {

    SellerProfile findBySellerProfileName(String SellerName);

    SellerProfile findByEmail(String email);

}