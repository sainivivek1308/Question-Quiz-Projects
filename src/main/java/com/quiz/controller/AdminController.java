package com.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.Entity.Frontend.PostBasicData;
import com.quiz.database.UserDataDAO;
import com.quiz.service.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/admin")

public class AdminController {
	@Autowired
	UserDataDAO userjpa;
	
	@Autowired
	QuizService quizservice;
	
	@GetMapping("/viewall")
	public ResponseEntity<List<PostBasicData>> get(){
		List<PostBasicData> data=userjpa.findAll();
		return new ResponseEntity<List<PostBasicData>>(data,HttpStatus.OK);
	}
	@PostMapping("/createquiz")
	public ResponseEntity<String> createmethod(@RequestParam String category,
			@RequestParam int numq,@RequestParam String title,@RequestParam String categorytopic){
		System.out.println(categorytopic);
		return quizservice.createQuiz(category,numq,title,categorytopic); 
	}

}
