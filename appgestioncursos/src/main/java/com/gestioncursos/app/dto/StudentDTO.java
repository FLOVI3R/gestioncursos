package com.gestioncursos.app.dto;

public class StudentDTO {
	private Long idAlumnos;
	private String Nombre;
	private String Apellidos;
	private String email;
	private String password;
	private String foto;

	public Long getIdAlumnos() {
		return idAlumnos;
	}
	public void setIdAlumnos(Long idAlumnos) {
		this.idAlumnos = idAlumnos;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellidos() {
		return Apellidos;
	}
	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
}
