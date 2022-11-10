package com.example.demo.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.UserProfile;
import com.example.demo.infrastructure.UserProfileRepository;

@Service
public class UserProfileService {

	@Autowired
	private UserProfileRepository userProfileRepository;

	public List<UserProfile> listUserProfile() {
		return userProfileRepository.findAll();
	}

	public Optional<UserProfile> getUserProfile(String id) {
		return userProfileRepository.findById(id);
	}

	public UserProfile addUserProfile(UserProfile userProfile) {
		return userProfileRepository.save(userProfile);
	}
}
