package com.gestioncursos.app.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="alumnos")
public class Student implements Serializable{
	private static final long serialVersionUID = 729695826511553275L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAlumnos;
	
	@Column(name = "Nombre", length = 45, nullable = false)
	private String Nombre;
	
	@Column(name = "Apellidos", length = 45, nullable = false)
	private String Apellidos;
	
	@Column(name = "email", length = 45, unique = true, nullable = false)
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "foto", length = 45, nullable = true)
	private String foto;
	
	public Student() {
		super();
	}

	public Student(String nombre, String apellidos, String email, String password, String foto) {
		super();
		Nombre = nombre;
		Apellidos = apellidos;
		this.email = email;
		this.password = password;
		this.foto = foto;
	}

	public Student(Long idAlumnos, String nombre, String apellidos, String email, String password, String foto) {
		super();
		this.idAlumnos = idAlumnos;
		this.Nombre = nombre;
		this.Apellidos = apellidos;
		this.email = email;
		this.password = password;
		this.foto = foto;
	}
	
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
