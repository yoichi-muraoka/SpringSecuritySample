package com.example.app.domain;

import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class User implements UserDetails {
	
	private Integer id;
	
	private String name;
	
	@NotBlank
	private String loginId;
	
	@NotBlank
	private String loginPass;
	
	private List<String> roles;
	
	// ログイン認証に必要なIDの設定
	@Override
	public String getUsername() {
		return loginId;
	}
	
	// ログイン認証に必要なパスワードの設定
	@Override
	public String getPassword() {
		return loginPass;
	}
	
	// ロールのリストを返す
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles
				.stream()
				.map(role -> new SimpleGrantedAuthority(role))
				.toList();
	}
	
	/*
	 * ユーザーアカウントやパスワードの期限、
	 * アカウントの凍結等については、今回は実装しない 
	 */
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}