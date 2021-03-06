package com.accessProject.footballManager.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accessProject.footballManager.models.CreateFootballTeamRequestModel;
import com.accessProject.footballManager.models.CreateFootballTeamResponseModel;
import com.accessProject.footballManager.models.ListTeamsResponseModel;
import com.accessProject.footballManager.services.FootballManagerService;
import com.accessProject.footballManager.shared.FootballTeamDto;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/footballManager")
public class FootballControllerImp implements FootballController{

	@Autowired
	FootballManagerService footballManagerService;
	
	
	@GetMapping("/team/details")
	public ResponseEntity<CreateFootballTeamResponseModel> getTeam(String nombre) {
		return (ResponseEntity<CreateFootballTeamResponseModel>) footballManagerService.getTeam(nombre);
	}
	
	@GetMapping("/teams")
	public ResponseEntity<ListTeamsResponseModel> getAllTeams() {
		return (ResponseEntity<ListTeamsResponseModel>) footballManagerService.getAllTeams();
	}
	

	@GetMapping("/teamsByCapacity")
	public ResponseEntity<ListTeamsResponseModel> getAllTeamsByCapacity() {
		return (ResponseEntity<ListTeamsResponseModel>) footballManagerService.getAllTeamsByCapacity();
	}
	
	@PostMapping
	public ResponseEntity<CreateFootballTeamResponseModel> createFootballTeam(@Valid @RequestBody CreateFootballTeamRequestModel teamDetails) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		FootballTeamDto teamDto = modelMapper.map(teamDetails, FootballTeamDto.class);
		
		return (ResponseEntity<CreateFootballTeamResponseModel>) footballManagerService.createTeam(teamDto);
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteFootballTeam(String nombre) {
		return  footballManagerService.deleteTeam(nombre);
	}
	
	@PatchMapping
	public ResponseEntity<CreateFootballTeamResponseModel> updateFootballTeam(@Valid @RequestBody CreateFootballTeamRequestModel teamDetails) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		FootballTeamDto teamDto = modelMapper.map(teamDetails, FootballTeamDto.class);
		
		return (ResponseEntity<CreateFootballTeamResponseModel>) footballManagerService.updateTeam(teamDto);
	}
}
