package com.arquisoft1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arquisoft1.entities.Social;
import com.arquisoft1.service.ISocialService;

@RestController
@RequestMapping("/social")
public class SocialRestController {
	
	@Autowired
	private ISocialService service;
	
	@GetMapping("/seguidores/{seguido}")
	public List<String> findSeguidores(@PathVariable String seguido){
		return service.findSeguidores(seguido);
	}
	
	@GetMapping("/seguidos/{seguidor}")
	public List<String> findSeguidos(@PathVariable String seguidor){		
		return service.findSeguidos(seguidor);
	}
	
	@PostMapping("/crear")
	public Social create(@RequestBody Social social) {		
		return service.create(social);
	}
	
	@GetMapping("/verificar")
	public boolean verify(@RequestBody Social social, BindingResult result) {
		Social socialFind = service.findSocial(social);	
		
		if(socialFind == null) {
			return false;
		}else {
			return true;
		}
	}
	
	@DeleteMapping("/eliminar")
	public void delete(@RequestBody Social social) {
		Social socialFind = service.findSocial(social);	
		service.delete(socialFind);
	}
	
}
