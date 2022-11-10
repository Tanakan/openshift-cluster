package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MenuDTO;
import com.example.demo.entity.Menu;
import com.example.demo.service.MenuService;


@RestController
@RequestMapping(path = "menues")
public class MenuController {

	@Autowired
	MenuService menuService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Menu> list() {
		return menuService.listMenus();
	}

	@RequestMapping(path = "{menuId}", method = RequestMethod.GET)
	public Menu get(@PathVariable String menuId) {
		return menuService.getMenu(menuId).get();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Menu create(@RequestBody MenuDTO menuDTO) {
		return null;
	}

	@RequestMapping(path="{menuId}", method = RequestMethod.PUT)
	public Menu update(@PathVariable String menuId, @RequestBody MenuDTO menuDTO) {
		return null;
	}
	
	@RequestMapping(path="{menuId}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String menuId) {
		
	}
}
