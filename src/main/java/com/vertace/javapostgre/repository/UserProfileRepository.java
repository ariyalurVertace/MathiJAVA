package com.vertace.javapostgre.repository;

import com.vertace.javapostgre.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    UserProfile findByUserName(String userName);

    UserProfile findByEmail(String email);

    UserProfile findByUserNameAndEmail(String userName, String email );

}