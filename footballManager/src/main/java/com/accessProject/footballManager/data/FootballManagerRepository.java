package com.accessProject.footballManager.data;

import org.springframework.data.repository.CrudRepository;

public interface FootballManagerRepository extends CrudRepository<FootballTeamEntity, Long>{
	FootballTeamEntity findByNombre(String nombre);
}
