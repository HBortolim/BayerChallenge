package com.grupoM.BayerChallenge.dto;

import java.io.Serializable;

import com.grupoM.BayerChallenge.entities.Address;
import com.grupoM.BayerChallenge.entities.User;



public class AddressDTO implements Serializable{
	
	private Long id;	
	private String tipo_logradouro;
	private String cep;
	private String street;
	private Integer number;
	private String complement;
	private String neighborhood;
	private String city;
	private String state;
	private String country;
	
	private User user;
	
	public AddressDTO() {
		
	}

	public AddressDTO(Long id, String tipo_logradouro, String cep, String street, Integer number, String complement,
			String neighborhood, String city, String state, String country, User user) {
		super();
		this.id = id;
		this.tipo_logradouro = tipo_logradouro;
		this.cep = cep;
		this.street = street;
		this.number = number;
		this.complement = complement;
		this.neighborhood = neighborhood;
		this.city = city;
		this.state = state;
		this.country = country;
		this.user = user;
	}
	
	public AddressDTO(Address entity) {
		this.id = entity.getId();
		this.tipo_logradouro = entity.getTipo_logradouro();
		this.cep = entity.getCep();
		this.street = entity.getStreet();
		this.number = entity.getNumber();
		this.complement = entity.getComplement();
		this.neighborhood = entity.getNeighborhood();
		this.city = entity.getCity();
		this.state = entity.getState();
		this.country = entity.getCountry();
		this.user = entity.getUser();
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo_logradouro() {
		return tipo_logradouro;
	}

	public void setTipo_logradouro(String tipo_logradouro) {
		this.tipo_logradouro = tipo_logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
