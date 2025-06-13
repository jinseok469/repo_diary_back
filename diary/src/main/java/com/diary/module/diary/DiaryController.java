package com.diary.module.diary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins ="http://localhost:5173")
public class DiaryController {
	
	@Autowired
	DiaryService service;
	
	@GetMapping("/api/diary")
	public List<DiaryDto> selectDiary(@RequestParam("id") String id){
		
		return service.selectDiary(id) ;
	}
	
	@PostMapping("/api/diary/new")
	public DiaryDto insertDiary(@RequestBody DiaryDto dto) {
		System.out.println(dto.getUser_id());
		int message = service.insertDiary(dto);
		if(message > 0) {
			dto.setMessage("일기 생성이 성공하였습니다");
		}else {
			dto.setMessage("일기 생성이 실패하였습니다..");
		}
		return dto;
	}
	
	@PutMapping("/api/diary/edit")
	public DiaryDto updateDiary(@RequestBody DiaryDto dto) {
		int message = service.updateDiary(dto);
		
		if(message > 0) {
			dto.setMessage("일기 수정이 성공 하였습니다");
		}else {
			dto.setMessage("일기 수정이 실패 하였습니다");
		}
		return dto;
	}
	
	
	@PutMapping("/api/diary/uele")
	public DiaryDto ueleteDiary(@RequestBody DiaryDto dto) {
		int message = service.ueleteDiary(dto);
		if(message > 0 ) {
			dto.setMessage("일기가 삭제 되었습니다");
		}else {
			//by pass
		}
		return dto;
	}

}
