package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthPageController {

	// ログイン済み(権限は問わない)
	@GetMapping({"/loginUser", "/loginUser/home"})
	public String forLoginUsers() {
		return "login-user-page";
	}
	
	// NORMAL権限
	@GetMapping({"/normal", "/noraml/home"})
	public String forNormals() {
		return "normal-page";
	}
	
	// ADMIN権限
	@GetMapping({"/admin", "/admin/home"})
	public String forAdmins() {
		return "admin-page";
	}
	
}
