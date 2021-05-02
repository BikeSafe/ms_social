package com.arquisoft1.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.arquisoft1.entities.Social;

public interface ISocialDao extends CrudRepository<Social, Long> {
	
	public List<Social> findBySeguidor(String seguidor);
	
	public List<Social> findBySeguido(String seguido);
	
	public Social findBySeguidorAndSeguido(String seguidor, String seguido);

}
