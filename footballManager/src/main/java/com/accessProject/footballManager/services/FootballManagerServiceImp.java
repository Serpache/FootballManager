package com.accessProject.footballManager.services;

import java.util.*;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.accessProject.footballManager.data.FootballManagerRepository;
import com.accessProject.footballManager.data.FootballTeamEntity;
import com.accessProject.footballManager.shared.FootballTeamDto;
import com.accessProject.footballManager.shared.ResponseDto;

@Service
public class FootballManagerServiceImp implements FootballManagerService {

	@Autowired
	FootballManagerRepository footballManagerRepository;

	@Override
	public ResponseEntity<?> getTeam(String nombre) {
		try {
			FootballTeamEntity entity = footballManagerRepository.findByNombre(nombre);

			if (entity == null) {
				return new ResponseEntity<>(new ResponseDto("Error: no existe el equipo buscado"),
						HttpStatus.NOT_FOUND);

			}

			ModelMapper mapper = new ModelMapper();
			FootballTeamDto returnValue = mapper.map(entity, FootballTeamDto.class);
			return new ResponseEntity<>(returnValue, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseDto("Error en el servidor"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> getAllTeams() {
		try {
			Iterable<FootballTeamEntity> entityList = footballManagerRepository.findAll();

			List<FootballTeamDto> returnValue = new ArrayList<FootballTeamDto>();
			ModelMapper modelMapper = new ModelMapper();

			for (FootballTeamEntity elem : entityList) {
				FootballTeamDto dto = modelMapper.map(elem, FootballTeamDto.class);
				;
				returnValue.add(dto);
			}
			return new ResponseEntity<>(returnValue, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseDto("Error en el servidor"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> getAllTeamsByCapacity() {
		try {
			Iterable<FootballTeamEntity> entityList = footballManagerRepository.findAll();

			List<FootballTeamDto> returnValue = new ArrayList<FootballTeamDto>();
			ModelMapper modelMapper = new ModelMapper();

			for (FootballTeamEntity elem : entityList) {
				FootballTeamDto dto = modelMapper.map(elem, FootballTeamDto.class);
				;
				returnValue.add(dto);
			}

			returnValue = returnValue.stream().sorted(Comparator.comparingInt(FootballTeamDto::getCapacidad))
					.collect(Collectors.toList());

			return new ResponseEntity<>(returnValue, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseDto("Error en el servidor"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> createTeam(FootballTeamDto teamDetails) {
		switch (teamDetails.getDivision()) {
		case 1:
			if (teamDetails.getCapacidad() < 50000) {
				return new ResponseEntity<>(
						new ResponseDto("Un equipo de primera divisi??n requiere al menos 50000 de capacidad"),
						HttpStatus.BAD_REQUEST);
			}
		case 2:
			if (teamDetails.getCapacidad() < 10000) {
				return new ResponseEntity<>(
						new ResponseDto("Un equipo de segunda divisi??n requiere al menos 10000 de capacidad"),
						HttpStatus.BAD_REQUEST);
			}
		case 3:
			if (teamDetails.getCapacidad() < 3000) {
				return new ResponseEntity<>(
						new ResponseDto("Un equipo de tercera divisi??n requiere al menos 3000 de capacidad"),
						HttpStatus.BAD_REQUEST);
			}

		}
		if (teamDetails.getNombre() == "") {
			return new ResponseEntity<>(new ResponseDto("El nombre del equipo es obligatorio"), HttpStatus.BAD_REQUEST);
		}
		try {
			FootballTeamEntity entity = footballManagerRepository.findByNombre(teamDetails.getNombre());

			if (entity != null) {
				return new ResponseEntity<>(new ResponseDto("Error: ya existe un equipo con ese nombre"),
						HttpStatus.BAD_REQUEST);
			}
			teamDetails.setEquipoId(UUID.randomUUID().toString());
			teamDetails.setFechaCreacion(new Date());

			ModelMapper modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			FootballTeamEntity footballTeamEntity = modelMapper.map(teamDetails, FootballTeamEntity.class);

			FootballTeamDto returnValue = modelMapper.map(footballManagerRepository.save(footballTeamEntity),
					FootballTeamDto.class);

			return new ResponseEntity<>(returnValue, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseDto("Error en el servidor"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> deleteTeam(String nombre) {
		try {
			FootballTeamEntity entity = footballManagerRepository.findByNombre(nombre);

			if (entity == null) {
				return new ResponseEntity<>(new ResponseDto("Error: no existe el equipo que intentas eliminar"),
						HttpStatus.NOT_FOUND);
			}

			footballManagerRepository.delete(entity);
			return new ResponseEntity<>(new ResponseDto(nombre + " eliminado con exito"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseDto("Error en el servidor"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> updateTeam(FootballTeamDto teamDetails) {
		switch (teamDetails.getDivision()) {
		case 1:
			if (teamDetails.getCapacidad() < 50000) {
				return new ResponseEntity<>(
						new ResponseDto("Un equipo de primera divisi??n requiere al menos 50000 de capacidad"),
						HttpStatus.BAD_REQUEST);
			}
		case 2:
			if (teamDetails.getCapacidad() < 10000) {
				return new ResponseEntity<>(
						new ResponseDto("Un equipo de segunda divisi??n requiere al menos 10000 de capacidad"),
						HttpStatus.BAD_REQUEST);
			}
		case 3:
			if (teamDetails.getCapacidad() < 3000) {
				return new ResponseEntity<>(
						new ResponseDto("Un equipo de tercera divisi??n requiere al menos 3000 de capacidad"),
						HttpStatus.BAD_REQUEST);
			}

		}
		if (teamDetails.getNombre() == "") {
			return new ResponseEntity<>(new ResponseDto("El nombre del equipo es obligatorio"), HttpStatus.BAD_REQUEST);
		}
		try {
			FootballTeamEntity entity = footballManagerRepository.findByNombre(teamDetails.getNombre());

			if (entity == null) {
				return new ResponseEntity<>(new ResponseDto("Error: no existe el equipo que intentas modificar"),
						HttpStatus.NOT_FOUND);
			}

			ModelMapper modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			FootballTeamEntity footballTeamEntity = modelMapper.map(teamDetails, FootballTeamEntity.class);
			footballTeamEntity.setEquipoId(entity.getEquipoId());
			footballTeamEntity.setId(entity.getId());

			FootballTeamDto returnValue = modelMapper.map(footballManagerRepository.save(footballTeamEntity),
					FootballTeamDto.class);

			return new ResponseEntity<>(returnValue, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseDto("Error en el servidor"), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
