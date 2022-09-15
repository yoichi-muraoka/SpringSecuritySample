package com.example.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests(requests -> {
			requests.antMatchers("/", "/home", "/css/**").permitAll();
			requests.antMatchers("/normal/**").hasRole("NORMAL");
			requests.antMatchers("/admin/**").hasRole("ADMIN");
			requests.anyRequest().authenticated();
		})
		.formLogin(form -> {
			form.loginPage("/login")
			    .usernameParameter("loginId")
			    .passwordParameter("loginPass")
			    .failureForwardUrl("/loginFailure")
			    .permitAll();
		})
		.logout(logout -> {
			logout.invalidateHttpSession(true)
			      .logoutSuccessUrl("/logoutDone")
			      .permitAll();
		});

		return http.build();
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}





