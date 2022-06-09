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
import javax.persistence.PrePersist;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "tb_ocorrencia")
public class Ocurrence implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant date;
	
	@OneToOne
	@JoinColumn(name = "symptom_id")
	private Symptom symptoms;
	
	@OneToOne
	@JoinColumn(name = "disease_id")
	private Disease disease;
	
	public Ocurrence() {
		
	}

	public Ocurrence(Long id, Instant moment, Disease disease) {
		super();
		this.id = id;
		this.date = moment;
		this.disease = disease;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return date;
	}

	public void setMoment(Instant moment) {
		this.date = moment;
	}

//	public List<Symptom> getSymptoms() {
//		return symptoms;
//	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}
	
	@PrePersist
	public void prePersist() {
		date = Instant.now();
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
