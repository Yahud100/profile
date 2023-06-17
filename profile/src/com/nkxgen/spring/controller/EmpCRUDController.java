package com.nkxgen.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nkxgen.spring.orm.model.Employee;
import com.nkxgen.spring.orm.servic.EmpService;

@Controller
public class EmpCRUDController {

	EmpService empserv;
	String global;

	@Autowired
	public EmpCRUDController(EmpService empserv) {
		this.empserv = empserv;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String employeeLogin(Model model) {
		System.out.println("empLogin JSP Requested");
		return "login";
	}

	@RequestMapping(value = "/tofront", method = RequestMethod.GET)
	public String enterIntoMenu(@RequestParam("email") String email) {
		global = email;
		System.out.println(global);
		return "front";
	}

	@RequestMapping(value = "/toprofile", method = RequestMethod.GET)
	public String getAllDetailsEmploye(Model model) {

		System.out.println("this is sp_orm controller getting employes method ");
		Employee empdetails = empserv.getByEmail(global);

		// set it to the model
		model.addAttribute("empdet", empdetails);

		// call the view
		return "profile";
	}

}
