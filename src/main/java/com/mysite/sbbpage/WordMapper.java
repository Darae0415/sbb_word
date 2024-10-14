package com.mysite.sbbpage;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WordMapper {
	List<Word> getWordList(@Param("offset")int offset,@Param("size") int size);
	//Param : 명시적으로 파라미터이름을 전달
	Integer countTotal();
	Word getWordById(Integer id);
	
}
