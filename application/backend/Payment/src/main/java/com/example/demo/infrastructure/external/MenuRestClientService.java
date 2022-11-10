package com.example.demo.infrastructure.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MenuRestClientService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${app.menu.url}")
	private String url;

	public Menu getMenu(long menuId) {
		return restTemplate.getForObject(url, Menu.class, menuId);
	}
}
