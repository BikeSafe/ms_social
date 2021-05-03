package com.arquisoft1.service;

import java.util.List;

import com.arquisoft1.entities.Social;

public interface ISocialService {
	
	public Social create(Social social); 
	
	List<String> findSeguidos(String seguidor);
	
	List<String> findSeguidores(String seguido);
	
	public void delete(Social social);
	
	public Social findSocial(Social social);
}
