package com.accessProject.footballManager.shared;

import java.io.Serializable;
import java.util.Date;

public class FootballTeamDto implements Serializable{

	private static final long serialVersionUID = -612099230454014692L;
	
	private String nombre;
	private String ciudad;
	private String propietario;
	private int capacidad;
	private int division;
	private String competicion;
	private int numJugadores;
	private Date fechaCreacion;
	private String equipoId;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getPropietario() {
		return propietario;
	}
	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	public int getDivision() {
		return division;
	}
	public void setDivision(int division) {
		this.division = division;
	}
	public String getCompeticion() {
		return competicion;
	}
	public void setCompeticion(String competicion) {
		this.competicion = competicion;
	}
	public int getNumJugadores() {
		return numJugadores;
	}
	public void setNumJugadores(int numJugadores) {
		this.numJugadores = numJugadores;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getEquipoId() {
		return equipoId;
	}
	public void setEquipoId(String equipoId) {
		this.equipoId = equipoId;
	}
	
	@Override
	public String toString() {
		return "FootballTeamDto [nombre=" + nombre + ", ciudad=" + ciudad + ", propietario=" + propietario
				+ ", capacidad=" + capacidad + ", division=" + division + ", competicion=" + competicion
				+ ", numJugadores=" + numJugadores + ", fechaCreacion=" + fechaCreacion + ", equipoId=" + equipoId
				+ "]";
	}
}
