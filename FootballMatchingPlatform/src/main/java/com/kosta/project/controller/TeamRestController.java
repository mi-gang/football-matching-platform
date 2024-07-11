package com.kosta.project.controller;

import org.springframework.web.bind.annotation.RestController;

import com.kosta.project.service.TeamService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TeamRestController {

	private final TeamService ts;
	
	
	
}
