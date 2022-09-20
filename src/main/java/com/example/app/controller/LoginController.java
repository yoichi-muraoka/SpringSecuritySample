package com.example.app.controller;

import javax.validation.Valid;

import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.domain.User;

@Controller
public class LoginController {

	// ログイン画面の表示
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "login-form";
	}

	// ログイン失敗時
	@PostMapping("/loginFailure")
	public String loginFailure(@Valid User user, Errors errors,
			@RequestAttribute("SPRING_SECURITY_LAST_EXCEPTION") AuthenticationException ex, Model model) {
		if (!errors.hasErrors()) {
			errors.reject(ex.getClass().getName());
		}

		return "login-form";
	}

	// ログアウト完了時
	@GetMapping("/logoutDone")
	public String logoutDone(RedirectAttributes ra) {
		ra.addFlashAttribute("logoutMsg", "ログアウトしました");
		return "redirect:/login";
	}

}
