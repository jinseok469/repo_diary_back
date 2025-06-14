package com.diary.module.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.diary.module.jwt.JwtUtil;
import com.diary.module.util.BaseVo;

import jakarta.annotation.PostConstruct;

@RestController
public class UserController {
	
	@Autowired
	UserService service;
	@Autowired
	JwtUtil jwtUtil;
	@Autowired
	BaseVo vo;
	
	@PostMapping("/api/login")
	public ResponseEntity<?> loginUser(@RequestBody UserDto dto){
		dto.setPassword(dto.getPassword().toLowerCase());
		UserDto user = service.loginUser(dto);
		if(user == null || "".equals(user)) {
			dto.setMessage("아이디나 비밀번호를 다시 확인해주세요");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(dto);
		}
		
		boolean pwd = vo.matchesBcrypt(dto.getPassword(), user.getPassword(), 10);	
			
		if(!pwd) {
			dto.setMessage("아이디나 비밀번호를 다시 확인해주세요");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(dto);
		}else {
			//by pass
		}
		String token = jwtUtil.generateToken(user.getId(),user.getName());
		return ResponseEntity.ok(token);
	}
	
	@PostMapping("/api/insert")
	public ResponseEntity<String> insertUser(@RequestBody UserDto dto){
		dto.setPassword(dto.getPassword().toLowerCase());
		dto.setPassword(vo.encodeBcrypt(dto.getPassword(), 10));
		
		
		int state = service.insertUser(dto);
		
		if(state > 0) {
			dto.setMessage("회원가입 성공");
		}else {
			dto.setMessage("회원가입 실패");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(dto.getMessage());
		}
		
		return ResponseEntity.ok(dto.getMessage());
	}
	
		

}
