package com.grupoM.BayerChallenge.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;

import java.util.List;

import com.grupoM.BayerChallenge.entities.Ocurrence;
import com.grupoM.BayerChallenge.entities.Symptom;


public class OcurrenceDTO implements Serializable{

	
	private Long id;
	//private Instant moment;
	private List<SymptomDTO> symptoms = new ArrayList<>();
	private DiseaseDTO disease;
	
	public OcurrenceDTO() {
		
	}

	public OcurrenceDTO(Long id, Instant moment, DiseaseDTO disease) {
		super();
		this.id = id;
		//this.moment = moment;
		this.disease = disease;
	}
	
	public OcurrenceDTO(Ocurrence entity) {
		this.id = entity.getId();
		//this.moment = entity.getMoment();
		this.disease = new DiseaseDTO(entity.getDisease());
	}
	
	public OcurrenceDTO(Ocurrence entity, List<Symptom> symptoms) {
		this(entity);
		symptoms.forEach(symptom -> this.symptoms.add(new SymptomDTO(symptom)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public Instant getMoment() {
//		return moment;
//	}
//
//	public void setMoment(Instant moment) {
//		this.moment = moment;
//	}

	public List<SymptomDTO> getSymptoms() {
		return symptoms;
	}

	public DiseaseDTO getDisease() {
		return disease;
	}

	public void setDisease(DiseaseDTO disease) {
		this.disease = disease;
	}
	
}
