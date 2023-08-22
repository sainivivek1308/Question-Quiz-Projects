package com.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.Entity.Question_Wrapper;
import com.quiz.Entity.Quiz_Entity;
import com.quiz.Entity.Response_Question;
import com.quiz.Entity.Frontend.PostBasicData;
import com.quiz.Entity.Frontend.QuizQuestionUI;
import com.quiz.service.QuizService;


@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {
	
	@Autowired
	QuizService quizservice;
	
	@Autowired
	QuizQuestionUI questionquiz;
	/*
	@PostMapping("/create")
	public ResponseEntity<String> createmethod(@RequestParam String category,
			@RequestParam int numq,@RequestParam String title,@RequestParam String categorytopic){
		System.out.println(categorytopic);
		return quizservice.createQuiz(category,numq,title,categorytopic); 
	}*/
	
	/* post the basic start quiz detail*/
	/*get back the object of id and question*/
	
	@PostMapping("/startquiz")
	public ResponseEntity<QuizQuestionUI> getDataQuiz(@RequestBody PostBasicData postdata){
		return quizservice.getdatauser(postdata);
	}
	@GetMapping("/getall")
	public ResponseEntity<List<Quiz_Entity>> getdetail(){
		return quizservice.getinfo();
	}
	@GetMapping("/gettitle/{title}")
	public ResponseEntity<List<Question_Wrapper>> gettitle(@PathVariable String title){
		return quizservice.gettitle(title);
	}
	
	
	@GetMapping("/getId/{id}")
	public ResponseEntity<List<Question_Wrapper>> getdetailbyid(@PathVariable Integer id){
		return quizservice.getQuizQuestion(id);
	}
	
	@PostMapping("/submit/{id}")
	public ResponseEntity<String> submitinfo(@PathVariable int id,@RequestBody List<Response_Question> question){
		return quizservice.calculateresult(id, question);
	}
}
