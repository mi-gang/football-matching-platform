package com.kosta.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.project.domain.User;

public interface UserListRepository extends JpaRepository<User, String>{
	//List<User> findByUseridLikeOrNicknameLikeOrNameLikeOrPhoneNumberLikeOrEmailLikeOrAddressLike(String userId, String nickname, String name, String phoneNumber, String email, String address);
}
