package com.example.demo.presentation;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.application.UserProfileService;
import com.example.demo.domain.UserProfile;

@RestController
@CrossOrigin
public class UserProfileController {

	@Autowired
	private UserProfileService userProfileService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public List<UserProfileDTO> listUserProfile() {
		List<UserProfile> userProfiles = userProfileService.listUserProfile();
		List<UserProfileDTO> response = modelMapper.map(userProfiles, new TypeToken<List<UserProfileDTO>>() {
		}.getType());
		return response;
	}

	@GetMapping(path = "/{id}")
	public UserProfile getUserProfile(@PathVariable String id) {
		Optional<UserProfile> userProfile = userProfileService.getUserProfile(id);
		if (userProfile.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return userProfile.get();
	}
}
