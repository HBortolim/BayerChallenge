package com.grupoM.BayerChallenge.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "tb_doenca")
public class Disease implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String name;
    private String scientificName;
    
    @ManyToMany
	@JoinTable(name = "tb_rl_doenca_sintoma",
		joinColumns = @JoinColumn(name = "id_doenca"),
		inverseJoinColumns = @JoinColumn(name = "id_sintoma")			
			)
    private List<Symptom> symptoms = new ArrayList<>();
    
    @OneToOne()
    @MapsId
    @JoinColumn(name = "ocurrence_id")
    private Ocurrence ocurrence;

    public Disease() {
    }

    public Disease(Long id, String name, String scientificName) {
        this.id = id;
        this.name = name;
        this.scientificName = scientificName;
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

	public List<Symptom> getSymptoms() {
		return symptoms;
	}
	
	public Ocurrence getOcurrence() {
		return ocurrence;
	}

	public void setOcurrence(Ocurrence ocurrence) {
		this.ocurrence = ocurrence;
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
		Disease other = (Disease) obj;
		return Objects.equals(id, other.id);
	}
    
}
