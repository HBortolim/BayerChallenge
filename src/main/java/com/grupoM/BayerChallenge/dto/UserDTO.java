package com.grupoM.BayerChallenge.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.grupoM.BayerChallenge.entities.Ocurrence;
import com.grupoM.BayerChallenge.entities.User;

public class UserDTO implements Serializable{


	private Long id;
	private String name;
	private String gender;
	private String email;
	private String cpf;
	//private Instant birthDay;
	private AddressDTO address;
	private List<OcurrenceDTO> ocurrences = new ArrayList<>();

	public UserDTO() {
		super();
	}

	public UserDTO(Long id, String name, String gender, String email, String cpf, Instant birthDay) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.cpf = cpf;
		//this.birthDay = birthDay;
	}
	
	public UserDTO(User entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.gender = entity.getGender();
		this.email = entity.getEmail();
		this.cpf = entity.getCpf();
		//this.birthDay = entity.getBirthDay();
	}
	
	public UserDTO(User entity,List<Ocurrence> ocurrences) {
		this(entity);
		ocurrences.forEach(ocurrence -> this.ocurrences.add(new OcurrenceDTO(ocurrence)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

//	public Instant getBirthDay() {
//		return birthDay;
//	}
//
//	public void setBirthDay(Instant birthDay) {
//		this.birthDay = birthDay;
//	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public List<OcurrenceDTO> getOcurrences() {
		return ocurrences;
	}

	public void setOcurrences(List<OcurrenceDTO> ocurrences) {
		this.ocurrences = ocurrences;
	}	
	
}
