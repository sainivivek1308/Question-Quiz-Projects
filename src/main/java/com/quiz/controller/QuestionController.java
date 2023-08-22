package com.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.Entity.Question;
import com.quiz.service.QuestionService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/question")
public class QuestionController {
	@Autowired
	QuestionService service;
	
	@GetMapping("/get")
	//Handle the Exception using Response Entity
	public ResponseEntity<List<Question>> getallQuestion() {
		return service.getalldetail();
	}
	@GetMapping("/getcategory/{category}")
	public ResponseEntity<List<Question>> getbcategory(@PathVariable String category){
		return service.getbycateg(category.toLowerCase());
	}
	@PostMapping("/post")
	public ResponseEntity<String> PostQuestion(@RequestBody Question quiz) {
			//System.out.println(quiz);
			return  service.post(quiz);
			
	}
	@GetMapping("/getId/{id}")
	public ResponseEntity<Question> get(@PathVariable String id) {
		return service.getbyiddetail(id);
	}
	
	@DeleteMapping("/DeletId/{id}")
	public ResponseEntity<String> deletebyid(@PathVariable String id) {
		return service.deletebyid(id);
		
	}
	
	
}
