package com.diary.module.diary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diary.module.openai.OpenAiDiaryService;

import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins ="http://localhost:5173")
public class DiaryController {
	
	@Autowired
	DiaryService service;
	@Autowired
	OpenAiDiaryService aiService;
	
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
	
	@PostMapping("/api/satistics")
	public ResponseEntity<?> emotionId (@RequestBody List<DiaryDto> dto) {
		System.out.println("연결성공");
		 if (dto.isEmpty()) {
		        return ResponseEntity.badRequest().body("데이터 없음");
		    }
		 
		int emotion = 0;
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		int count4 = 0;
		int count5 = 0;
	
		int percentage = 0;

		

		Map<Object, Object> result = new HashMap<>();
		result.put("emotionId", emotion); // 최빈값
		result.put("percentage", percentage); // 최빈값의 퍼센트
		
		for(int i = 0 ; i < dto.size(); i++) {
			if(1 == dto.get(i).getEmotionId()) {
				count1++;
			}
			else if(2 == dto.get(i).getEmotionId()) {
				count2++;
			}
			else if(3 == dto.get(i).getEmotionId()) {
				count3++;
			}
			else if(4 == dto.get(i).getEmotionId()) {
				count4++;
			}
			else if(5 == dto.get(i).getEmotionId()) {
				count5++;
			}
		}
		percentage = (int) Math.round((double) count1 / dto.size() * 100);
		result.put(1, percentage);
		percentage = (int) Math.round((double) count2 / dto.size() * 100);
		result.put(2, percentage);
		percentage = (int) Math.round((double) count3 / dto.size() * 100);
		result.put(3, percentage);
		percentage = (int) Math.round((double) count4 / dto.size() * 100);
		result.put(4, percentage);
		percentage = (int) Math.round((double) count5 / dto.size() * 100);
		result.put(5, percentage);
		
		
		return ResponseEntity.ok(result);
	    
	}
	
	@PostMapping("api/ai")
    public Mono<String> analyzeDiary(@RequestBody List<DiaryDto> diary) {
		List<String> diaries = new ArrayList<String>();
		for(int i = 0 ; i < diary.size(); i++) {
			diaries.add(diary.get(i).getContent());
		}
		
        return aiService.analyzeMonthlyDiary(diaries);
    }
}
