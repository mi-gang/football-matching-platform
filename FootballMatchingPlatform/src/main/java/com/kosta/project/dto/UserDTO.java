package com.kosta.project.dto;


import java.util.Collection;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements UserDetails {
	
	private String userId;
	private String name;
	private String password;
	private String nickname;
	private Date birthday;
	private String gender;
	private String phoneNumber;
	private String email;
	private String address;
	private int gameCount;
	private int winCount;
	private int userRank;
	private float percentile;
	private Date lastLoginDate;
	private Date joinDate;
	private int userScore;
	private String userTier;
	private int reportCount;
	private Date suspendedTime;
	private String userStatus;
	private float winRate;
	private String teamName;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}

	@Override
	public String getUsername() {
		return userId;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {	// 만료 여부
		return true;	// 만료되지 않았음
	}

	@Override
	public boolean isAccountNonLocked() {	// 잠금 여부
		return true;	// 잠기지 않았음
	}

	@Override
	public boolean isCredentialsNonExpired() {	// 패스워드 만료 여부
		return true;	// 만료되지 않았음
	}

	@Override
	public boolean isEnabled() {	// 계정 사용 가능 여부
		return true;	// 사용 가능함
	}
}
