package com.mysite.sbbpage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WordController {
	@Autowired
	private WordService wordService;
	
	// /words?page=2&size=10
	@GetMapping("/words")
	@ResponseBody
	public List<Word> getWord(@RequestParam(name="page", defaultValue="1") int page,
			@RequestParam(name="size", defaultValue="10") int size) {
		return wordService.getWordList(page, size);
	}
	
	@GetMapping("/words/{id}")
	@ResponseBody
	public Word getWord(@PathVariable("id") Integer id) {
		return wordService.getWordById(id);
	}
	
}
