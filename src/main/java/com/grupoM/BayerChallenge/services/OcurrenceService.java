package com.grupoM.BayerChallenge.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.grupoM.BayerChallenge.dto.OcurrenceDTO;
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
public class OcurrenceService {

	@Autowired
	private OcurrenceRepository repository;
	
	@Autowired
	private SymptomRepository symptomRepository;
	
	@Autowired
	private DiseaseRepository diseaseRepository;
	
	@Transactional(readOnly = true)
	public Page<OcurrenceDTO> findAllPaged(PageRequest pageRequest){
		Page<Ocurrence> list = repository.findAll(pageRequest);
		return list.map(x -> new OcurrenceDTO(x));
	}

	@Transactional(readOnly = true)
	public OcurrenceDTO findById(Long id) {
		Optional<Ocurrence> obj = repository.findById(id);
		Ocurrence entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found!"));
		return new OcurrenceDTO(entity);
	}

	@Transactional
	public OcurrenceDTO insertOcurrence(OcurrenceDTO dto) {
		Ocurrence entity = new Ocurrence();
		copyDtoToEntity(dto,entity);
		entity = repository.save(entity);
		return new OcurrenceDTO(entity);
	}

	@Transactional
	public OcurrenceDTO updateOcurrence(Long id, OcurrenceDTO dto) {
		try {
			Ocurrence entity = repository.getReferenceById(id);
			copyDtoToEntity(dto,entity);
//			entity.getSymptoms().clear();
//			for(SymptomDTO sympDto : dto.getSymptoms()) {
//				Symptom symptom = symptomRepository.getReferenceById(sympDto.getId());
//				entity.getSymptoms().add(symptom);
//			}
			entity = repository.save(entity);
			return new OcurrenceDTO(entity);
		}
		catch(javax.persistence.EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found "+ id);
		}
	}

	public void deleteOcurrence(Long id) {
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
	private void copyDtoToEntity(OcurrenceDTO dto, Ocurrence entity) {
		Disease disease = diseaseRepository.getReferenceById(dto.getDisease().getId());
		entity.setDisease(disease);
	}
	
}
