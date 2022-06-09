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
import com.grupoM.BayerChallenge.entities.Ocurrence;
import com.grupoM.BayerChallenge.entities.Symptom;
import com.grupoM.BayerChallenge.repositories.DiseaseRepository;
import com.grupoM.BayerChallenge.repositories.OcurrenceRepository;
import com.grupoM.BayerChallenge.repositories.SymptomRepository;
import com.grupoM.BayerChallenge.services.exceptions.DatabaseException;
import com.grupoM.BayerChallenge.services.exceptions.ResourceNotFoundException;

@Service
public class DiseaseService {

	@Autowired
	private DiseaseRepository repository;
	
	@Autowired
	private SymptomRepository symptomRepository;
	
	@Autowired
	private OcurrenceRepository ocurrenceRepository;
	
	@Transactional(readOnly = true)
	public Page<DiseaseDTO> findAllPaged(PageRequest pageRequest){
		Page<Disease> list = repository.findAll(pageRequest);
		return list.map(x -> new DiseaseDTO(x));
	}

	@Transactional(readOnly = true)
	public DiseaseDTO findById(Long id) {
		Optional<Disease> obj = repository.findById(id);
		Disease entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found!"));
		return new DiseaseDTO(entity, entity.getSymptoms());
	}

	@Transactional
	public DiseaseDTO insertDisease(DiseaseDTO dto) {
		Disease entity = new Disease();
		copyDtoToEntity(dto,entity);
		entity = repository.save(entity);
		return new DiseaseDTO(entity);
	}

	@Transactional
	public DiseaseDTO updateDisease(Long id, DiseaseDTO dto) {
		try {
			Disease entity = repository.getReferenceById(id);
			copyDtoToEntity(dto,entity);
			entity = repository.save(entity);
			return new DiseaseDTO(entity);
		}
		catch(javax.persistence.EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found "+ id);
		}
	}

	public void deleteDisease(Long id) {
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
	public void copyDtoToEntity(DiseaseDTO dto, Disease entity) {
		
		entity.setName(dto.getName());
		entity.setScientificName(dto.getScientificName());
		Ocurrence ocurrence = ocurrenceRepository.getReferenceById(dto.getOcurrence().getId());
		entity.setOcurrence(ocurrence);
		
		entity.getSymptoms().clear();
		for(SymptomDTO symptomDto : dto.getSymptoms()) {
			Symptom symptom = symptomRepository.getReferenceById(symptomDto.getId());
			entity.getSymptoms().add(symptom);
		}
	}
	
}
