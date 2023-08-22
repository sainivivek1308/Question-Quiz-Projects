	package com.quiz.Entity.Frontend;

import java.util.List;

import org.springframework.stereotype.Component;

import com.quiz.Entity.Question_Wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizQuestionUI {
	private Integer id;
	private List<Question_Wrapper> question;
	
}
