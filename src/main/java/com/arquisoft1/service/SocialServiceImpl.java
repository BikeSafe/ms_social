package com.arquisoft1.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arquisoft1.entities.Social;
import com.arquisoft1.repository.ISocialDao;


@Service
public class SocialServiceImpl implements ISocialService {
	
	@Autowired
	private ISocialDao dao;

	@Override
	public Social create(Social social) {
		return dao.save(social);
	}

	@Override
	public List<String> findSeguidos(String seguidor) {
		List<Social> listSocial = dao.findBySeguidor(seguidor);
		List<String> listSeguidos = listSocial.stream()
				.map(social -> social.getSeguido())
				.collect(Collectors.toList());
		
		return listSeguidos;
	}

	@Override
	public List<String> findSeguidores(String seguido) {
		List<Social> listSocial = dao.findBySeguido(seguido);
		List<String> listSeguidores = listSocial.stream()
				.map(social -> social.getSeguidor())
				.collect(Collectors.toList());
		
		return listSeguidores;
	}

	@Override
	public boolean verify(String seguidor, String seguido) {
		Social social = dao.findBySeguidorAndSeguido(seguidor, seguido);
		
		if(social == null) {
			return false;
		}else{
			return true;
		}
	}

	@Override
	public void delete(String seguidor, String seguido) {
		Social social = dao.findBySeguidorAndSeguido(seguidor, seguido);
		dao.delete(social);
	}

}
