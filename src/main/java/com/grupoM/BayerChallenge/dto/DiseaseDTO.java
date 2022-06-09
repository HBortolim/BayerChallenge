package com.grupoM.BayerChallenge.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.grupoM.BayerChallenge.entities.Disease;
import com.grupoM.BayerChallenge.entities.Symptom;

public class DiseaseDTO implements Serializable{

	
	private Long id;
    private String name;
    private String scientificName;

    private List<SymptomDTO> symptoms = new ArrayList<>();
    private OcurrenceDTO ocurrence;

    public DiseaseDTO() {
    }

    public DiseaseDTO(Long id, String name, String scientificName,OcurrenceDTO ocurrence) {
        this.id = id;
        this.name = name;
        this.scientificName = scientificName;
        this.ocurrence = ocurrence;
    }
    
    public DiseaseDTO(Disease entity) {
    	this.id = entity.getId();
        this.name = entity.getName();
        this.scientificName = entity.getScientificName();
        this.ocurrence = new OcurrenceDTO(entity.getOcurrence());
    }
    
    public DiseaseDTO(Disease entity, List<Symptom> symptoms) {
		this(entity);
		symptoms.forEach(symptom -> this.symptoms.add(new SymptomDTO(symptom)));
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

	public String getScientificName() {
		return scientificName;
	}

	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}

	public List<SymptomDTO> getSymptoms() {
		return symptoms;
	}

	public OcurrenceDTO getOcurrence() {
		return ocurrence;
	}

	public void setOcurrence(OcurrenceDTO ocurrence) {
		this.ocurrence = ocurrence;
	}
}
