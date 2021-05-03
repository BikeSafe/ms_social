package com.arquisoft1.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "sociales",
uniqueConstraints = @UniqueConstraint(columnNames = {"seguidor","seguido"}, name = "RelacionUnica"))
public class Social {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="esta vacio")
	@Size(min=36, max=36, message="es incorrecto")
	@Column(nullable = false, length = 36)
	private String seguidor;
	
	@NotEmpty(message="esta vacio")
	@Size(min=36, max=36, message="es incorrecto")
	@Column(nullable = false, length = 36)
	private String seguido;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSeguidor() {
		return seguidor;
	}
	public void setSeguidor(String seguidor) {
		this.seguidor = seguidor;
	}
	public String getSeguido() {
		return seguido;
	}
	public void setSeguido(String seguido) {
		this.seguido = seguido;
	}
		
}
