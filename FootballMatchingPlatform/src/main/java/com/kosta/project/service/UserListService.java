package com.kosta.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kosta.project.domain.User;
import com.kosta.project.repository.UserListRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserListService {

	private final UserListRepository ulr;
	
	
	public List<User> getUserList() {
		return ulr.findAll();
	}
	
	
	/*
	 * public List<User> searchUserList(String sarchText) { return ulr.
	 * findByUseridLikeOrNicknameLikeOrNameLikeOrPhoneNumberLikeOrEmailLikeOrAddressLike
	 * (sarchText, sarchText, sarchText, sarchText, sarchText, sarchText); }
	 */
	
}
