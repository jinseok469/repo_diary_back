package com.diary.module.diary;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryDao {
	
	public List<DiaryDto> selectDiary(@Param(value="id") String id);
	public int insertDiary(DiaryDto dto);
	public int updateDiary(DiaryDto dto);
	public int deleteDiary(DiaryDto dto);
	public int ueleteDiary(DiaryDto dto);

}
