package com.gestioncursos.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gestioncursos.app.dto.StudentDTO;
import com.gestioncursos.app.entity.Student;
import com.gestioncursos.app.service.IStudentService;

@RestController
@Controller
public class StudentController {
	
	private String REGISTER_VIEW = "register";
	private IStudentService studentService;
	
	public StudentController(IStudentService studentService, PasswordEncoder encoder) {
		super();
		this.studentService = studentService;
		this.encoder = encoder;
	}

	@Autowired    
	private PasswordEncoder encoder;
	
	@ModelAttribute("student")
	public StudentDTO newStudentDTO() {
		return new StudentDTO();
	}
	
	@GetMapping("/auth/login")
	public String loginView(Model model, @RequestParam(name = "logout", required = false) String logout) {
		model.addAttribute("student", new Student());
		model.addAttribute("logout", logout);
        return "login";
	}
	
	@RequestMapping(value = "/student/register", method = RequestMethod.GET)
    public ModelAndView studentRegisterView() {
		ModelAndView maw = new ModelAndView("register");
		maw.addObject(new Student());
        return maw;
    }
	
	@RequestMapping(value="/student/index", method = RequestMethod.GET)
    public ModelAndView studentIndexView(Student student){
        ModelAndView maw = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Student st = (Student) studentService.loadUserByUsername(auth.getName());
        maw.addObject("userName", "Welcome " + st.getNombre() + "/" + st.getEmail());
        maw.setViewName("studentIndex");
        return maw;
    }
	
	@RequestMapping(value = "/student/registerStudent", method = RequestMethod.POST)
	public String createNewStudent(@ModelAttribute("student") StudentDTO stDTO, BindingResult result, Model model, RedirectAttributes flash) {
		Student existingStudent = studentService.findStudentByEmail(stDTO.getEmail());
		
        if(existingStudent != null && existingStudent.getEmail() != null && !existingStudent.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "Ya existe una cuenta creada con esta dirección de email. Por favor introduzca otro correo.");
        }
		
        if(result.hasErrors()){
            model.addAttribute("student", stDTO);
            flash.addFlashAttribute("error", "error not found");
            return "redirect:/student/register";	
        }else {
            studentService.saveStudent(stDTO);
            flash.addFlashAttribute("success", "Alumno registrado con éxito");
            return "redirect:/student/login";
        }
	}
}
