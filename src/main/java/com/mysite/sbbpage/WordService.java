package com.mysite.sbbpage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordService {
	@Autowired
	//클래스명 객체명 = new 클래스명 하지않아도 @Autowired로 손 쉽게 객체화 할 수 있음.
	//인터페이스(mapper)는 new로 직접 객체를 생성하지 않음 그래서 구현체를 주입하기 위해 autowired씀
	private WordMapper wordMapper;
	
	public PageDTO getWordList(int page, int size) {
		int offset = (page - 1) * size;
		// offset은 page가 1일 경우, index가 0부터 시작하기 때문에 -1를 하고 * (size)를 한다
		// size는 페이징할때 (1페이지당 size만큼 목록이 있고 index를 위 식으로 계속 적용)
		// select * from table limit x offset y;
		// /words?page(limit)={size}&size(offset)={offset}
		
		int totalElements = wordMapper.countTotal();
		// int타입으로 totalElements에 wordMapper.countTotal();를 가져와서 저장
		int totalPages = (int) Math.ceil((double) totalElements / size);
		// int타입으로 totalPages에 (double)타입으로 totalElements(카운터된 데이터값)/size를 나눈다
		// 여기서 why? double인가 정수형으로 계산하면 나머지자릿수가 생략되기 때문에 (올바른 페이지를 나눌 수 없음)실수로 나눔
        // Math.ceil() : 나머지 소수를 무조건 "올림"으로 해줌(2.1 -> 3, 2.5 -> 3)
		// 그래서 totalPages = int형(정수)으로 계산식을 받아와서 저장
		List<Word> content = wordMapper.getWordList(offset, size);
		//List<Word> : Word 필드들을 객체화해서 담아 편리하게 사용하는 List (page, size, totalPages, totalElements, content
		// List<Word> content안에 wordMapper.getWordList(offset, size); 값을 저장
		PageDTO pageDTO = new PageDTO(page, size, totalPages, totalElements, content);
		// 클래스명 객체명 = new 클래스명(매개변수(객체값));
		// page,size = 컨트롤러에서 받아온 값
		// totalPages = service에서 계산한 값
		// totalElements = mapper에서 데이터베이스 행을 카운트한 값
		// content = service에서 매개변수만 변경한 값(offset, size);
		return pageDTO;
	}
	public Word getWordById(Integer id) {
		return wordMapper.getWordById(id);
		
	}
}