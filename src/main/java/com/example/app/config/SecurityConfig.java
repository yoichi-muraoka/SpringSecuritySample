package com.example.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
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
		.formLogin();

		return http.build();
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public UserDetailsManager userDetailsManager() {
		UserDetails taro = User.withUsername("taro")
				.password(passwordEncoder().encode("pass"))
				.roles("NORMAL", "ADMIN")
				.build();
		
		UserDetails hana = User.withUsername("hana")
				.password(passwordEncoder().encode("pass"))
				.roles("NORMAL")
				.build();
		
		UserDetails jiro = User.withUsername("jiro")
				.password(passwordEncoder().encode("pass"))
				.roles("ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(taro, hana, jiro);
	}

}





