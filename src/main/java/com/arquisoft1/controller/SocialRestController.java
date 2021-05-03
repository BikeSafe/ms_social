package com.arquisoft1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> findSeguidores(@PathVariable String seguido){
		
		List<String> list = null;
		Map<String, Object> response = new HashMap<>();
		
		if(seguido.length() != 36) {
			response.put("errores", "uuid incorrecto");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			list = service.findSeguidores(seguido);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<String>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/seguidos/{seguidor}")
	public ResponseEntity<?> findSeguidos(@PathVariable String seguidor){
		
		List<String> list = null;
		Map<String, Object> response = new HashMap<>();
		
		if(seguidor.length() != 36) {
			response.put("errores", "uuid incorrecto");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			list = service.findSeguidos(seguidor);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<String>>(list, HttpStatus.OK);
	}
	
	@PostMapping("/crear")
	public ResponseEntity<?> create(@Valid @RequestBody Social social, BindingResult result) {
		
		Social socialNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo " + err.getField() +" "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errores", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			socialNew =service.create(social);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return new ResponseEntity<Social>(socialNew, HttpStatus.CREATED);
	}
	
	@GetMapping("/verificar")
	public ResponseEntity<?> verify(@Valid @RequestBody Social social, BindingResult result) {
		Social socialFind = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo " + err.getField() +" "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errores", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			socialFind = service.findSocial(social);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(socialFind == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}else {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/eliminar")
	public ResponseEntity<?> delete(@Valid @RequestBody Social social, BindingResult result) {
		
		Social socialFind = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo " + err.getField() +" "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errores", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			socialFind = service.findSocial(social);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(socialFind == null) {
			response.put("mensaje", "La relacion seguidor seguido no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}else {
			try {
				service.delete(socialFind);
				response.put("mensaje", "eliminacion exitosa");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al realizar la consulta");
				response.put("error", e.getMessage());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}
		
	}
	
}
