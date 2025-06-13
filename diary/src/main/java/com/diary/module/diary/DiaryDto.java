package com.diary.module.diary;


public class DiaryDto {
	
	 	private Long id;
	    private String createdDate;
	    private Integer emotionId;
	    private String content;
	    private String message;
	    private Integer delNy;
	    private Long user_id;
	    
	    
	    
	    
	    
		public Long getUser_id() {
			return user_id;
		}
		public void setUser_id(Long user_id) {
			this.user_id = user_id;
		}
		public Integer getDelNy() {
			return delNy;
		}
		public void setDelNy(Integer delNy) {
			this.delNy = delNy;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(String createdDate) {
			this.createdDate = createdDate;
		}
		public Integer getEmotionId() {
			return emotionId;
		}
		public void setEmotionId(Integer emotionId) {
			this.emotionId = emotionId;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}

}
