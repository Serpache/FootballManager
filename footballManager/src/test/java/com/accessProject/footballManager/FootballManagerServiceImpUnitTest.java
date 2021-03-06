package com.accessProject.footballManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import java.util.*;

import javax.persistence.EntityExistsException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.accessProject.footballManager.data.FootballManagerRepository;
import com.accessProject.footballManager.data.FootballTeamEntity;
import com.accessProject.footballManager.services.FootballManagerServiceImp;
import com.accessProject.footballManager.shared.FootballTeamDto;
import com.accessProject.footballManager.shared.ResponseDto;

@SuppressWarnings("unchecked")
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@SpringBootTest
class FootballManagerServiceImpUnitTest {

	@InjectMocks
	FootballManagerServiceImp footballManagerService;

	@Mock
	FootballManagerRepository footballManagerRepository;

	private FootballTeamDto newTeam = new FootballTeamDto();

	@BeforeEach
	void testInitialization() {
		newTeam.setNombre("Real Madrid");
		newTeam.setCiudad("Madrid");
		newTeam.setPropietario("Florentino");
		newTeam.setDivision(1);
		newTeam.setCompeticion("Liga");
		newTeam.setNumJugadores(22);
		newTeam.setCapacidad(60000);
		newTeam.setFechaCreacion(new Date());
	}

	@Nested
	class getTeamDetailsResponse {
		@Test
		void shouldReturnOneTeam_WhenValidTeamNameIsProvided() {
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			FootballTeamEntity footballTeamEntity = modelMapper.map(newTeam, FootballTeamEntity.class);

			when(footballManagerRepository.findByNombre("Real Madrid")).thenReturn(footballTeamEntity);
			ResponseEntity<FootballTeamDto> response = (ResponseEntity<FootballTeamDto>) footballManagerService
					.getTeam(newTeam.getNombre());

			verify(footballManagerRepository, times(1)).findByNombre("Real Madrid");
			assertEquals(HttpStatus.OK, response.getStatusCode());
		}
		
		@Test
		void shouldReturnServerError_WhenRepositoryFails() {
			when(footballManagerRepository.findByNombre("Real Madrid")).thenThrow(new EntityExistsException());
			ResponseEntity<?> response = footballManagerService.getTeam(newTeam.getNombre());

			assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
			assertEquals("Error en el servidor", ((ResponseDto) response.getBody()).getDescription());
		}
		
		@Test
		void shouldReturnNotFound_WhenValidTeamNameDoNotExist() {
			when(footballManagerRepository.findByNombre("Real Madrid")).thenReturn(null);
			ResponseEntity<?> response = footballManagerService.getTeam(newTeam.getNombre());

			verify(footballManagerRepository, times(1)).findByNombre("Real Madrid");
			assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
			assertEquals("Error: no existe el equipo buscado",
					((ResponseDto) response.getBody()).getDescription());
		}
	}
	
	@Nested
	class getAllTeamsResponse {
		@Test
		void shouldReturnListOfTeams_WhenTeamsExist() {
			ResponseEntity<FootballTeamDto> response = (ResponseEntity<FootballTeamDto>) footballManagerService.getAllTeams();

			verify(footballManagerRepository, times(1)).findAll();
			assertEquals(HttpStatus.OK, response.getStatusCode());
		}
		
		@Test
		void shouldReturnServerError_WhenRepositoryFails() {
			when(footballManagerRepository.findAll()).thenThrow(new EntityExistsException());
			ResponseEntity<?> response = footballManagerService.getAllTeams();

			assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
			assertEquals("Error en el servidor", ((ResponseDto) response.getBody()).getDescription());
		}
	}
	
	@Nested
	class getAllTeamsByCapacityResponse {
		@Test
		void shouldReturnListOfTeams_WhenTeamsExist() {
			ResponseEntity<FootballTeamDto> response = (ResponseEntity<FootballTeamDto>) footballManagerService.getAllTeamsByCapacity();

			verify(footballManagerRepository, times(1)).findAll();
			assertEquals(HttpStatus.OK, response.getStatusCode());
		}
		
		@Test
		void shouldReturnServerError_WhenRepositoryFails() {
			when(footballManagerRepository.findAll()).thenThrow(new EntityExistsException());
			ResponseEntity<?> response = footballManagerService.getAllTeamsByCapacity();

			assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
			assertEquals("Error en el servidor", ((ResponseDto) response.getBody()).getDescription());
		}
	}

	@Nested
	class createTeamResponse {
		@Test
		void shouldCallSaveFromRepository_WhenValidTeamIsCreated() {
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			FootballTeamEntity footballTeamEntity = modelMapper.map(newTeam, FootballTeamEntity.class);

			when(footballManagerRepository.save(any(FootballTeamEntity.class))).thenReturn(footballTeamEntity);
			ResponseEntity<FootballTeamDto> response = (ResponseEntity<FootballTeamDto>) footballManagerService
					.createTeam(newTeam);

			verify(footballManagerRepository, times(1)).save(any(FootballTeamEntity.class));
			assertEquals(HttpStatus.OK, response.getStatusCode());
		}

		@Test
		void shouldReturnServerError_WhenRepositoryFails() {
			ResponseEntity<?> response = (ResponseEntity<FootballTeamDto>) footballManagerService.createTeam(newTeam);

			assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
			assertEquals("Error en el servidor", ((ResponseDto) response.getBody()).getDescription());
		}
		
		@Test
		void shouldReturnBadRequest_WhenNameIsEmpty() {
			newTeam.setNombre("");
			ResponseEntity<?> response = (ResponseEntity<FootballTeamDto>) footballManagerService.createTeam(newTeam);

			assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
			assertEquals("El nombre del equipo es obligatorio",
					((ResponseDto) response.getBody()).getDescription());
		}
		
		@Test
		void shouldReturnBadRequest_WhenNameIsAlreadyExist() {
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			FootballTeamEntity footballTeamEntity = modelMapper.map(newTeam, FootballTeamEntity.class);
			newTeam.setNombre("Real Madrid");
			when(footballManagerRepository.findByNombre("Real Madrid")).thenReturn(footballTeamEntity);
			ResponseEntity<?> response = (ResponseEntity<FootballTeamDto>) footballManagerService.createTeam(newTeam);

			assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
			assertEquals("Error: ya existe un equipo con ese nombre",
					((ResponseDto) response.getBody()).getDescription());
		}

		@Test
		void shouldReturnBadRequest_WhenDivisionIs1AndCapacityUnder50000() {
			newTeam.setDivision(1);
			newTeam.setCapacidad(49000);
			ResponseEntity<?> response = (ResponseEntity<FootballTeamDto>) footballManagerService.createTeam(newTeam);

			assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
			assertEquals("Un equipo de primera divisi??n requiere al menos 50000 de capacidad",
					((ResponseDto) response.getBody()).getDescription());
		}

		@Test
		void shouldReturnBadRequest_WhenDivisionIs2AndCapacityUnder10000() {
			newTeam.setDivision(2);
			newTeam.setCapacidad(9000);
			ResponseEntity<?> response = (ResponseEntity<FootballTeamDto>) footballManagerService.createTeam(newTeam);

			assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
			assertEquals("Un equipo de segunda divisi??n requiere al menos 10000 de capacidad",
					((ResponseDto) response.getBody()).getDescription());
		}

		@Test
		void shouldReturnBadRequest_WhenDivisionIs3AndCapacityUnder3000() {
			newTeam.setDivision(3);
			newTeam.setCapacidad(2000);
			ResponseEntity<?> response = (ResponseEntity<FootballTeamDto>) footballManagerService.createTeam(newTeam);

			assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
			assertEquals("Un equipo de tercera divisi??n requiere al menos 3000 de capacidad",
					((ResponseDto) response.getBody()).getDescription());
		}
	}
	
	@Nested
	class deleteTeamResponse {
		@Test
		void shouldReturnOk_WhenTeamsIsDeleted() {
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			FootballTeamEntity footballTeamEntity = modelMapper.map(newTeam, FootballTeamEntity.class);

			when(footballManagerRepository.findByNombre("Real Madrid")).thenReturn(footballTeamEntity);
			ResponseEntity<?> response = footballManagerService.deleteTeam(newTeam.getNombre());

			verify(footballManagerRepository, times(1)).findByNombre("Real Madrid");
			assertEquals(HttpStatus.OK, response.getStatusCode());
			assertEquals("Real Madrid eliminado con exito",
					((ResponseDto) response.getBody()).getDescription());
		}
		
		@Test
		void shouldReturnServerError_WhenRepositoryFails() {
			when(footballManagerRepository.findByNombre("Real Madrid")).thenThrow(new EntityExistsException());
			ResponseEntity<?> response = footballManagerService.deleteTeam(newTeam.getNombre());

			assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
			assertEquals("Error en el servidor", ((ResponseDto) response.getBody()).getDescription());
		}
		
		@Test
		void shouldReturnNotFound_WhenValidTeamNameDoNotExist() {
			when(footballManagerRepository.findByNombre("Real Madrid")).thenReturn(null);
			ResponseEntity<?> response = footballManagerService.deleteTeam(newTeam.getNombre());

			verify(footballManagerRepository, times(1)).findByNombre("Real Madrid");
			assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
			assertEquals("Error: no existe el equipo que intentas eliminar",
					((ResponseDto) response.getBody()).getDescription());
		}
	}

	@Nested
	class updateTeamResponse {
		@Test
		void shouldCallSaveFromRepository_WhenValidTeamIsUpdated() {
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			FootballTeamEntity footballTeamEntity = modelMapper.map(newTeam, FootballTeamEntity.class);

			when(footballManagerRepository.findByNombre("Real Madrid")).thenReturn(footballTeamEntity);
			when(footballManagerRepository.save(any(FootballTeamEntity.class))).thenReturn(footballTeamEntity);
			ResponseEntity<FootballTeamDto> response = (ResponseEntity<FootballTeamDto>) footballManagerService
					.updateTeam(newTeam);

			verify(footballManagerRepository, times(1)).save(any(FootballTeamEntity.class));
			assertEquals(HttpStatus.OK, response.getStatusCode());
		}

		@Test
		void shouldReturnServerError_WhenRepositoryFails() {
			when(footballManagerRepository.findByNombre("Real Madrid")).thenThrow(new EntityExistsException());
			ResponseEntity<?> response = (ResponseEntity<FootballTeamDto>) footballManagerService.updateTeam(newTeam);

			assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
			assertEquals("Error en el servidor", ((ResponseDto) response.getBody()).getDescription());
		}
		
		@Test
		void shouldReturnBadRequest_WhenNameIsEmpty() {
			newTeam.setNombre("");
			ResponseEntity<?> response = (ResponseEntity<FootballTeamDto>) footballManagerService.updateTeam(newTeam);

			assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
			assertEquals("El nombre del equipo es obligatorio",
					((ResponseDto) response.getBody()).getDescription());
		}

		@Test
		void shouldReturnBadRequest_WhenDivisionIs1AndCapacityUnder50000() {
			newTeam.setDivision(1);
			newTeam.setCapacidad(49000);
			ResponseEntity<?> response = (ResponseEntity<FootballTeamDto>) footballManagerService.updateTeam(newTeam);

			assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
			assertEquals("Un equipo de primera divisi??n requiere al menos 50000 de capacidad",
					((ResponseDto) response.getBody()).getDescription());
		}

		@Test
		void shouldReturnBadRequest_WhenDivisionIs2AndCapacityUnder10000() {
			newTeam.setDivision(2);
			newTeam.setCapacidad(9000);
			ResponseEntity<?> response = (ResponseEntity<FootballTeamDto>) footballManagerService.updateTeam(newTeam);

			assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
			assertEquals("Un equipo de segunda divisi??n requiere al menos 10000 de capacidad",
					((ResponseDto) response.getBody()).getDescription());
		}

		@Test
		void shouldReturnBadRequest_WhenDivisionIs3AndCapacityUnder3000() {
			newTeam.setDivision(3);
			newTeam.setCapacidad(2000);
			ResponseEntity<?> response = (ResponseEntity<FootballTeamDto>) footballManagerService.updateTeam(newTeam);

			assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
			assertEquals("Un equipo de tercera divisi??n requiere al menos 3000 de capacidad",
					((ResponseDto) response.getBody()).getDescription());
		}
		
		@Test
		void shouldReturnNotFound_WhenValidTeamNameDoNotExist() {
			when(footballManagerRepository.findByNombre("Real Madrid")).thenReturn(null);
			ResponseEntity<?> response = footballManagerService.updateTeam(newTeam);

			verify(footballManagerRepository, times(1)).findByNombre("Real Madrid");
			assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
			assertEquals("Error: no existe el equipo que intentas modificar",
					((ResponseDto) response.getBody()).getDescription());
		}
	}
}
