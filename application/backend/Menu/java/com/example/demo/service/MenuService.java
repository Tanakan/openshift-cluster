package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Menu;
import com.example.demo.repository.MenuRepository;

@Service
public class MenuService {

	@Autowired
	MenuRepository menuRepository;
	
	public void createMenu(Menu menu) {
		menuRepository.save(menu);
	}
	
	public Optional<Menu> getMenu(String menuId) {
		return null;
		//return menuRepository.findById(menuId);
	}
	
	public List<Menu> listMenus(){
		return menuRepository.findAll();
	}
	
}
