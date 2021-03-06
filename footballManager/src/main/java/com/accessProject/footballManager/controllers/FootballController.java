package com.accessProject.footballManager.controllers;

import javax.validation.Valid;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@RestController
@RequestMapping("/footballManager")
public interface FootballController {
	
	@Operation(description = "Get team details")
	@ApiResponse(responseCode = "200", description = "Successfully got team details")
	@ApiResponse(responseCode = "500", description = "Server error")
	@ApiResponse(responseCode = "404", description = "Team not found")
	@GetMapping("/team/details")
	public ResponseEntity<CreateFootballTeamResponseModel> getTeam(String nombre);
	
	@Operation(description = "Get all teams")
	@ApiResponse(responseCode = "200", description = "Successfully got list of all teams")
	@ApiResponse(responseCode = "500", description = "Server error")
	@GetMapping("/teams")
	public ResponseEntity<ListTeamsResponseModel> getAllTeams();
	
	@Operation(description = "Get all teams ordered by capacity")
	@ApiResponse(responseCode = "200", description = "Successfully got list of all teams ordered by capacity")
	@ApiResponse(responseCode = "500", description = "Server error")
	@GetMapping("/teams")
	public ResponseEntity<ListTeamsResponseModel> getAllTeamsByCapacity();
	
	@Operation(description = "Create new team")
	@ApiResponse(responseCode = "200", description = "Successfully created new team")
	@ApiResponse(responseCode = "500", description = "Server error")
	@ApiResponse(responseCode = "400", description = "Some field does not meet the requirements")
	@PostMapping
	public ResponseEntity<CreateFootballTeamResponseModel> createFootballTeam(@Valid @RequestBody CreateFootballTeamRequestModel teamDetails);
	
	@Operation(description = "Delete football team based on name")
	@ApiResponse(responseCode = "200", description = "Successfully deleted team")
	@ApiResponse(responseCode = "500", description = "Server error")
	@ApiResponse(responseCode = "404", description = "Team not found")
	@DeleteMapping
	public ResponseEntity<?> deleteFootballTeam(String nombre);
	
	@Operation(description = "Update football team details")
	@ApiResponse(responseCode = "200", description = "Successfully updated team")
	@ApiResponse(responseCode = "500", description = "Server error")
	@ApiResponse(responseCode = "404", description = "Team not found")
	@PatchMapping
	public ResponseEntity<CreateFootballTeamResponseModel> updateFootballTeam(@Valid @RequestBody CreateFootballTeamRequestModel teamDetails);
}
