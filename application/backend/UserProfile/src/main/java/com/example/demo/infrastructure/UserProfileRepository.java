package com.example.demo.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {

}
