package com.grupoM.BayerChallenge.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import com.grupoM.BayerChallenge.entities.Disease;
import com.grupoM.BayerChallenge.entities.Ocurrence;
import com.grupoM.BayerChallenge.entities.Symptom;

public class SymptomDTO implements Serializable{

	
	private Long id;
	private String name;
	private List<DiseaseDTO> diseases = new ArrayList<>();
	
	public SymptomDTO() {
		
	}

	public SymptomDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public SymptomDTO(Symptom entity) {
		this.id = entity.getId();
		this.name = entity.getName();
	}
	
	public SymptomDTO(Symptom entity, List<Disease> diseases) {
		this(entity);
		diseases.forEach(disease -> this.diseases.add(new DiseaseDTO(disease)));
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

	public List<DiseaseDTO> getDiseases() {
		return diseases;
	}

	public void setDiseases(List<DiseaseDTO> diseases) {
		this.diseases = diseases;
	}


}
