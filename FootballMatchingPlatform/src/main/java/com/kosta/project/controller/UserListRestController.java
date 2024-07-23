package com.kosta.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.project.domain.User;
import com.kosta.project.service.UserListService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserListRestController {
	private final UserListService uls;
	
	
	@GetMapping("/api/user/list")
	public Map<String, List<User>> getUserList() {
		return Map.of("result", uls.getUserList());
	}
	
	
	/*
	 * @PostMapping("/api/user/list") public Map<String, List<User>>
	 * postUserList(@RequestBody String searchText) { return Map.of("result",
	 * uls.searchUserList(searchText)); }
	 */
	
}
