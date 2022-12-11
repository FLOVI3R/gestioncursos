package com.gestioncursos.app.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gestioncursos.app.dto.StudentDTO;
import com.gestioncursos.app.entity.Student;
import com.gestioncursos.app.repository.StudentRepository;

@Service
public class StudentServiceImpl implements UserDetailsService{

	private StudentRepository repo;
	private PasswordEncoder encoder;
	
	public StudentServiceImpl(StudentRepository repo, PasswordEncoder encoder) {
		super();
		this.repo = repo;
		this.encoder = encoder;
	}

	public Student saveStudent(StudentDTO stDTO) {
		Student st = new Student(stDTO.getNombre(), stDTO.getApellidos(), stDTO.getEmail(), encoder.encode(stDTO.getPassword()), stDTO.getFoto());
		return repo.save(st);
	}

	public Student findStudentByEmail(String email) {
		return repo.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Student st = repo.findByEmail(email);
		UserBuilder builder = null;
		if(st == null) {
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}else {
			builder = User.withUsername(st.getNombre() + st.getApellidos());
			builder.disabled(false);
			builder.password(st.getPassword());
		}
		return builder.build();
	}
}
