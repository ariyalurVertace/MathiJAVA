package com.vertace.javapostgre.repository;

import com.vertace.javapostgre.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    UserProfile findByUserProfileName(String userName);

    UserProfile findByEmail(String email);

}