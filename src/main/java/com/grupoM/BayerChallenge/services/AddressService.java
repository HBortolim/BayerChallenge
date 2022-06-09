package com.grupoM.BayerChallenge.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupoM.BayerChallenge.dto.AddressDTO;
import com.grupoM.BayerChallenge.entities.Address;
import com.grupoM.BayerChallenge.repositories.AddressRepository;
import com.grupoM.BayerChallenge.services.exceptions.DatabaseException;
import com.grupoM.BayerChallenge.services.exceptions.ResourceNotFoundException;


@Service
public class AddressService {

	@Autowired
	private AddressRepository repository;
	
	
	@Transactional(readOnly = true)
	public Page<AddressDTO> findAllPaged(PageRequest pageRequest){
		Page<Address> list = repository.findAll(pageRequest);
		return list.map(x -> new AddressDTO(x));
	}

	@Transactional(readOnly = true)
	public AddressDTO findById(Long id) {
		Optional<Address> obj = repository.findById(id);
		Address entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found!"));
		return new AddressDTO(entity);
	}

	@Transactional
	public AddressDTO insertAddress(AddressDTO dto) {
		Address entity = new Address();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new AddressDTO(entity);
	}

	@Transactional
	public AddressDTO updateAddress(Long id, AddressDTO dto) {
		try {
			Address entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new AddressDTO(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found "+ id);
		}
	}

	public void deleteAddress(Long id) {
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
	private void copyDtoToEntity(AddressDTO dto, Address entity) {		
		entity.setTipo_logradouro(dto.getTipo_logradouro());
		entity.setCep(dto.getCep());
		entity.setStreet(dto.getStreet());
		entity.setNumber(dto.getNumber());
		entity.setComplement(dto.getComplement());
		entity.setNeighborhood(dto.getNeighborhood());
		entity.setCity(dto.getCity());
		entity.setState(dto.getState());
		entity.setCountry(dto.getCountry());
		entity.setUser(dto.getUser());
	}
}
