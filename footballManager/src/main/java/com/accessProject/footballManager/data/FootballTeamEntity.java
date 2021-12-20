package com.accessProject.footballManager.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="teams")
public class FootballTeamEntity implements Serializable{

	private static final long serialVersionUID = 8321650917797026972L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false, unique=true)
	private String nombre;
	private String ciudad;
	private String propietario;
	@Column(nullable=false)
	private int capacidad;
	@Column(nullable=false)
	private int division;
	private String competicion;
	@Column(nullable=false)
	private int numJugadores;
	@Column(nullable=false, updatable=false)
	private Date fechaCreacion;
	private String equipoId;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
		return "FootballTeamEntity [id=" + id + ", nombre=" + nombre + ", ciudad=" + ciudad + ", propietario="
				+ propietario + ", capacidad=" + capacidad + ", division=" + division + ", competicion=" + competicion
				+ ", numJugadores=" + numJugadores + ", fechaCreacion=" + fechaCreacion + ", equipoId=" + equipoId
				+ "]";
	}
}
