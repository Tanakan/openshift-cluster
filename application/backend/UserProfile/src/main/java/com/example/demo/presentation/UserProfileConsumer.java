package com.example.demo.presentation;

import java.io.IOException;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.demo.application.UserProfileService;
import com.example.demo.domain.UserProfile;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class UserProfileConsumer {

	@Autowired
	private UserProfileService userProfileService;

	@Autowired
	private ModelMapper modelMapper;

	@KafkaListener(topics = "${app.topic.debezium}")
	public void consume(byte[] message) throws StreamReadException, DatabindException, IOException {
		JSONObject jsonObject = new JSONObject(new String(message));
		String userProfileStr = jsonObject.getJSONObject("payload").getJSONObject("after").toString();
		UserProfileDTO userProfileDTO = new ObjectMapper().readValue(userProfileStr, UserProfileDTO.class);
		UserProfile userProfile = modelMapper.map(userProfileDTO, UserProfile.class);
		userProfileService.addUserProfile(userProfile);
	}
}
