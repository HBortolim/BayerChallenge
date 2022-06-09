package com.grupoM.BayerChallenge.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_ocorrencia")
public class Ocurrence implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	//private Instant moment;
	
	@OneToMany
	@JoinColumn(name = "ocurrence_id",referencedColumnName = "id")
	private List<Symptom> symptoms = new ArrayList<>();
	
	@OneToOne
    @JoinColumn(name="disease_id",referencedColumnName = "id")
	private Disease disease;
	
	public Ocurrence() {
		
	}

	public Ocurrence(Long id, Instant moment, Disease disease) {
		super();
		this.id = id;
		//this.moment = moment;
		this.disease = disease;
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

//	public void setMoment(Instant moment) {
//		this.moment = moment;
//	}

	public List<Symptom> getSymptoms() {
		return symptoms;
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ocurrence other = (Ocurrence) obj;
		return Objects.equals(id, other.id);
	}

	
	
}
