package com.arquisoft1.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sociales")
public class Social {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String uuid_seguidor;
	
	@Column(nullable = false)
	private String uuid_seguido;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUuid_seguidor() {
		return uuid_seguidor;
	}
	public void setUuid_seguidor(String uuid_seguidor) {
		this.uuid_seguidor = uuid_seguidor;
	}
	public String getUuid_seguido() {
		return uuid_seguido;
	}
	public void setUuid_seguido(String uuid_seguido) {
		this.uuid_seguido = uuid_seguido;
	}
		
}
