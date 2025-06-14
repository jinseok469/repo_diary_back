package com.diary.module.openai;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

@Service
public class OpenAiDiaryService {
	
	 @Value("${open_ai_secret_key}")
	    private String apiKey;
	 	
	
	    
	    public Mono<String> analyzeMonthlyDiary(List<String> diaries) {
	    	System.out.println(diaries);
	    	  WebClient webClient = WebClient.builder()
	    		        .baseUrl("https://api.openai.com/v1/chat/completions")
	    		        .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
	    		        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	    		        .build();
	        String diaryList = diaries.stream()
	            .map(d -> "- " + d)
	            .collect(Collectors.joining("\n"));

	        String prompt = """
	            아래는 사용자가 1달간 쓴 감정일기 목록입니다. 전체 내용을 분석해서:
	            1. 전체적인 감정 경향 요약
	            2. 주요 감정 통계 (예: 행복 10일, 슬픔 5일 등)
	            3. 따뜻한 위로 메시지 한 줄

	            일기 목록:
	            %s
	            """.formatted(diaryList);

	        Map<String, Object> requestBody = Map.of(
	            "model", "gpt-3.5-turbo",
	            "messages", List.of(Map.of("role", "user", "content", prompt))
	        );

	        return webClient.post()
	            .bodyValue(requestBody)
	            .retrieve()
	            .bodyToMono(String.class)
	            .map(response -> {
	                try {
	                    ObjectMapper mapper = new ObjectMapper();
	                    JsonNode root = mapper.readTree(response);
	                    return root.get("choices").get(0).get("message").get("content").asText();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                    return "분석 결과를 처리하는 중 오류가 발생했습니다.";
	                }
	            });
	    }

}
