package com.mysite.sbbpage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordService {
	@Autowired
	private WordMapper wordMapper;
	public List<Word> getWordList(int page, int size) {
		int offset = (page - 1) * size;
		// select * from table limit x offset y;
		// /words?page(limit)={size}&size(offset)={offset}
		return wordMapper.getWordList(offset, size);
	}
	public Word getWordById(Integer id) {
		return wordMapper.getWordById(id);
	}
}