package com.diary.module.diary;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryService {
	
	@Autowired
	DiaryDao diaryDao;
	
	public List<DiaryDto> selectDiary(@Param(value="id")String id){
		return diaryDao.selectDiary(id);
	}
	public int insertDiary(DiaryDto dto) {
		return diaryDao.insertDiary(dto);
	}
	public int updateDiary(DiaryDto dto) {
		return diaryDao.updateDiary(dto);
	}
	public int deleteDiary(DiaryDto dto) {
		return diaryDao.deleteDiary(dto);
	}
	public int ueleteDiary(DiaryDto dto) {
		return diaryDao.ueleteDiary(dto);
	}
	


}
