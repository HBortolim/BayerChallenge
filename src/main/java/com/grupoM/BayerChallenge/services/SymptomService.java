package com.grupoM.BayerChallenge.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupoM.BayerChallenge.dto.DiseaseDTO;
import com.grupoM.BayerChallenge.dto.SymptomDTO;
import com.grupoM.BayerChallenge.entities.Disease;
import com.grupoM.BayerChallenge.entities.Symptom;
import com.grupoM.BayerChallenge.repositories.DiseaseRepository;
import com.grupoM.BayerChallenge.repositories.SymptomRepository;
import com.grupoM.BayerChallenge.services.exceptions.DatabaseException;
import com.grupoM.BayerChallenge.services.exceptions.ResourceNotFoundException;


@Service
public class SymptomService {

	@Autowired
	private SymptomRepository repository;
	
	@Autowired
	private DiseaseRepository diseaseRepository;
	
	
	@Transactional(readOnly = true)
	public Page<SymptomDTO> findAllPaged(PageRequest pageRequest){
		Page<Symptom> list = repository.findAll(pageRequest);
		return list.map(x -> new SymptomDTO(x));
	}

	@Transactional(readOnly = true)
	public SymptomDTO findById(Long id) {
		Optional<Symptom> obj = repository.findById(id);
		Symptom entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found!"));
		return new SymptomDTO(entity, entity.getDiseases());
	}

	@Transactional
	public SymptomDTO insertSymptom(SymptomDTO dto) {
		Symptom entity = new Symptom();
		copyDtoToEntity(dto,entity);
		entity = repository.save(entity);
		return new SymptomDTO(entity);
	}

	@Transactional
	public SymptomDTO updateSymptom(Long id, SymptomDTO dto) {
		try {
			Symptom entity = repository.getReferenceById(id);
			copyDtoToEntity(dto,entity);
			entity = repository.save(entity);
			return new SymptomDTO(entity);
		}
		catch(javax.persistence.EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found "+ id);
		}
	}

	public void deleteSymptom(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found "+ id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation!");
		}		
	}
	private void copyDtoToEntity(SymptomDTO dto, Symptom entity) {
		
		entity.setName(dto.getName());	
		
		entity.getDiseases().clear();
		for(DiseaseDTO diseaseDto : dto.getDiseases()) {
			Disease disease = diseaseRepository.getReferenceById(diseaseDto.getId());
			entity.getDiseases().add(disease);
		}
	}
}
