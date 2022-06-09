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
import com.grupoM.BayerChallenge.dto.UserDTO;
import com.grupoM.BayerChallenge.entities.Ocurrence;
import com.grupoM.BayerChallenge.entities.User;
import com.grupoM.BayerChallenge.repositories.OcurrenceRepository;
import com.grupoM.BayerChallenge.repositories.UserRepository;
import com.grupoM.BayerChallenge.services.exceptions.DatabaseException;
import com.grupoM.BayerChallenge.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private OcurrenceRepository Ocurrencerepository;
	
	@Transactional(readOnly = true)
	public Page<UserDTO> findAllPaged(PageRequest pageRequest){
		Page<User> list = repository.findAll(pageRequest);
		return list.map(x -> new UserDTO(x));
	}

	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		Optional<User> obj = repository.findById(id);
		User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found!"));
		return new UserDTO(entity);
	}

	@Transactional
	public UserDTO insertUser(UserDTO dto) {
		User entity = new User();
		entity.setName(dto.getName());
		entity.setName(dto.getName());
		entity.setGender(dto.getGender());
		entity.setEmail(dto.getEmail());
		entity.setCpf(dto.getCpf());
		entity.setBirthDay(dto.getBirthDay());
		entity = repository.save(entity);
		return new UserDTO(entity);
	}

	@Transactional
	public UserDTO updateUser(Long id, UserDTO dto) {
		try {
			User entity = repository.getReferenceById(id);
			entity.setName(dto.getName());
			entity.setName(dto.getName());
			entity.setGender(dto.getGender());
			entity.setEmail(dto.getEmail());
			entity.setCpf(dto.getCpf());
			entity.setBirthDay(dto.getBirthDay());			
			entity.getOcurrences().clear();
			for(OcurrenceDTO ocuDto : dto.getOcurrences()) {
				Ocurrence ocurrence = Ocurrencerepository.getReferenceById(ocuDto.getId());
				entity.getOcurrences().add(ocurrence);
			}
			entity = repository.save(entity);
			return new UserDTO(entity);
		}
		catch(javax.persistence.EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found "+ id);
		}
	}

	public void deleteUser(Long id) {
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
}
