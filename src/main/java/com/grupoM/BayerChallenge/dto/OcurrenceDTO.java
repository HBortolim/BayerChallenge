package com.grupoM.BayerChallenge.dto;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;

import com.grupoM.BayerChallenge.entities.Ocurrence;
import com.grupoM.BayerChallenge.entities.Symptom;


public class OcurrenceDTO implements Serializable{

	
	private Long id;
	//private List<SymptomDTO> symptoms = new ArrayList<>();
	private DiseaseDTO disease;
	
	public OcurrenceDTO() {
		
	}

	public OcurrenceDTO(Long id, DiseaseDTO disease) {
		super();
		this.id = id;
		this.disease = disease;
	}
	
	public OcurrenceDTO(Ocurrence entity) {
		this.id = entity.getId();
		this.disease = new DiseaseDTO(entity.getDisease());
	}
	
//	public OcurrenceDTO(Ocurrence entity, List<Symptom> symptoms) {
//		this(entity);
//		symptoms.forEach(symptom -> this.symptoms.add(new SymptomDTO(symptom)));
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public List<SymptomDTO> getSymptoms() {
//		return symptoms;
//	}
//	
//
//	public void setSymptoms(List<SymptomDTO> symptoms) {
//		this.symptoms = symptoms;
//	}

	public DiseaseDTO getDisease() {
		return disease;
	}

	public void setDisease(DiseaseDTO disease) {
		this.disease = disease;
	}
	
}
