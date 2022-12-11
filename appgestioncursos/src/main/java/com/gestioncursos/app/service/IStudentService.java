package com.gestioncursos.app.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.gestioncursos.app.dto.StudentDTO;
import com.gestioncursos.app.entity.Student;

@Service
public interface IStudentService extends UserDetailsService {
	public Student saveStudent(StudentDTO stDTO);

	public Student findStudentByEmail(String email);
}
