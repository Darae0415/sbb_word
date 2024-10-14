package com.mysite.sbbpage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WordController {
	@Autowired
	private WordService wordService;
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	@GetMapping("/detail")
	public String detail() {
		return "detail";
	}
	
	
	@GetMapping("/words")
//	@ResponseBody
	public String getWord
	// /words?page=1(첫번째 페이지)&size=10(한 페이지당 10개의 항목표시)
		(@RequestParam(name="page", defaultValue="1") int page,
		//쿼리 파라미터(RequestParam)로 int타입 page에 저장받는데 클라이언트가 page값을 전달하지 않으면 디폴트값은 1로 설정
		@RequestParam(name="size", defaultValue="10") int size, 
		//쿼리 파라미터(RequestParam)로 int타입 size에 저장받는데 클라이언트가 page값을 전달하지 않으면 디폴트값은 10으로 설정
			Model model) {
		//Model 객체 : 컨트롤러에서 생성된 데이터를 HTML(뷰)로 전달하는 데 사용
			PageDTO pageDTO = wordService.getWordList(page, size);
			//PageDTO클래스를 pageDTO로 객체화 하면서 데이터값 wordService.getWordList(page, size);을 저장
			model.addAttribute("pageDTO", pageDTO);
			//pageDTO 객체를 모델에 추가해서 list.html에서 ${pageDTO}로 사용할 수 있게 함 
			return "list";
			//저장된 데이터를 list.html에 반환 + 뷰템플릿으로 list.html을 클라이언트에 표현
	}
	
	@GetMapping("/words/{id}")
	@ResponseBody
	public Word getWord(@PathVariable("id") Integer id) {
		return wordService.getWordById(id);
	}
	@GetMapping("/list")
	public String list() {
		return "list";
	}
	
}
