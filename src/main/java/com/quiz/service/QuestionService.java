package com.quiz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.Entity.Question;

import com.quiz.database.*;

import jakarta.persistence.Id;

@Service
public class QuestionService {

	@Autowired
	QuestionDao jpa;
	
	public ResponseEntity<List<Question>> getalldetail() {
		// TODO Auto-generated method stub
		try {
			
			return new ResponseEntity<>(jpa.findAll(),HttpStatus.OK);
		}
		catch (Exception e) {
			e.getStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		
		
	}
	public ResponseEntity<List<Question>> getbycateg(String category) {
		// TODO Auto-generated method stub
		try {
			if(!(jpa.findByCategory(category).isEmpty())) {
				return new ResponseEntity<>(jpa.findByCategory(category),HttpStatus.OK);
			}
		}
		catch (Exception e) {
			e.getStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NO_CONTENT);
		
	}
	
	public ResponseEntity<String> post(Question quiz) {
		Question question=new Question();
		question.setCategory(quiz.getCategory().toLowerCase());
		question.setCategorytopic(quiz.getCategorytopic().toLowerCase());
		question.setDifficultylevel(quiz.getDifficultylevel().toLowerCase());
		question.setOptions(quiz.getOptions());
		question.setQuestiontitle(quiz.getQuestiontitle());
		question.setRightanswer(quiz.getRightanswer());	
		jpa.save(question);
		return new ResponseEntity<String>("Sucess insert sucessfully",HttpStatus.CREATED);
		
		//return new ResponseEntity<>("Not insert sucessfully",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	public ResponseEntity<Question> getbyiddetail(String id) {
		int id1=Integer.parseInt(id);
		if(jpa.findbyIID(id1)!=null) {
			return new ResponseEntity<Question>(jpa.findbyIID(id1),HttpStatus.ACCEPTED);
		}
		else {
			Question p=null;
			return new ResponseEntity<Question>(p,HttpStatus.NO_CONTENT);
		}
		
	}

	public ResponseEntity<String> deletebyid(String id) {
		// TODO Auto-generated method stub
		ResponseEntity<Question> rs=getbyiddetail(id);
		
			if(rs.getStatusCode()!=HttpStatus.NO_CONTENT) {
				System.out.println("sucess");
				Integer id1=Integer.parseInt(id);
				jpa.deleteById(id1);
				return new ResponseEntity<String>("deleted Record Sucessfully",HttpStatus.OK);
			}
		
		return new ResponseEntity<String>("Record is not found that's why not delete ",HttpStatus.BAD_REQUEST);
	}
	
	
	
}
