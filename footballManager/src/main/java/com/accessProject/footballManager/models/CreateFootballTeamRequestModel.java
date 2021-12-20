package com.accessProject.footballManager.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

public class CreateFootballTeamRequestModel {

	@NotNull(message="Nombre del equipo obligatorio")
	private String nombre;
	private String ciudad;
	private String propietario;
	@Min(value = 1, message = "La capacidad del estadio no puede ser menor que 1")
	private int capacidad;
	@Min(value = 1, message = "La división mínima es 1")
	@Max(value = 3, message = "La división máxima es 3")
	private int division;
	private String competicion;
	@Min(value = 1, message = "El equipo debe tener al menos 1 jugador")
	private int numJugadores;
	
	@Schema(example = "Real Madrid")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Schema(example = "Madrid")
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	@Schema(example = "Florentino Pérez​")
	public String getPropietario() {
		return propietario;
	}
	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}
	@Schema(example = "20000")
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	@Schema(example = "1​")
	public int getDivision() {
		return division;
	}
	public void setDivision(int division) {
		this.division = division;
	}
	@Schema(example = "Liga​")
	public String getCompeticion() {
		return competicion;
	}
	public void setCompeticion(String competicion) {
		this.competicion = competicion;
	}
	@Schema(example = "22")
	public int getNumJugadores() {
		return numJugadores;
	}
	public void setNumJugadores(int numJugadores) {
		this.numJugadores = numJugadores;
	}	
}
