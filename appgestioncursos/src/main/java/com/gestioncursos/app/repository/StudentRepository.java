package com.gestioncursos.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestioncursos.app.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	Student findByEmail(String email);
}
