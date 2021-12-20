package com.accessProject.footballManager.services;

import org.springframework.http.ResponseEntity;

import com.accessProject.footballManager.shared.FootballTeamDto;

public interface FootballManagerService {

	ResponseEntity<?> getTeam(String nombre);
	ResponseEntity<?> getAllTeams();
	ResponseEntity<?> getAllTeamsByCapacity();
	ResponseEntity<?> createTeam(FootballTeamDto teamDetails);
	ResponseEntity<?> deleteTeam(String nombre);
	ResponseEntity<?> updateTeam(FootballTeamDto teamDetails);
}
