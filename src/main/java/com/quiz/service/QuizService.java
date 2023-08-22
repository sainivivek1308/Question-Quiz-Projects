package com.quiz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.Entity.Question;
import com.quiz.Entity.Question_Wrapper;
import com.quiz.Entity.Quiz_Entity;
import com.quiz.Entity.Response_Question;
import com.quiz.Entity.Frontend.PostBasicData;
import com.quiz.Entity.Frontend.QuizQuestionUI;
import com.quiz.database.QuestionDao;
import com.quiz.database.QuizDao;
import com.quiz.database.UserDataDAO;
@Service
public class QuizService {
	@Autowired
	QuizDao quizdao;

	@Autowired
	QuestionDao jpa;
	
	@Autowired
	UserDataDAO userJPA;
	@Autowired
	QuizQuestionUI quizuserdata;
	
	public  ResponseEntity<String> createQuiz(String category, int numq, String title,String categorytopic) {
		// TODO Auto-generated method stub
		List<Question> questions = null;
		questions=jpa.findrandombyCategory(category, numq,categorytopic);
		System.out.println(questions);
		Quiz_Entity quiz=new Quiz_Entity();
		quiz.setTitle(title);
		
		quiz.setCategory(category);
		quiz.setCategorytopic(categorytopic);
		quiz.setQuestion(questions);
		quizdao.save(quiz);
		return new ResponseEntity<String>("sucess",HttpStatus.CREATED);
	}

	public ResponseEntity<List<Quiz_Entity>> getinfo() {
		// TODO Auto-generated method stub
		try {
			
			return new ResponseEntity<>(quizdao.findAll(),HttpStatus.OK);
		}
		catch (Exception e) {
			e.getStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		
		
	}

	public ResponseEntity<List<Question_Wrapper>> gettitle(String title) {
		// TODO Auto-generated method stub
		List<Question_Wrapper> questionforuser=new ArrayList<>();
			try {
				if((quizdao.findByTitle(title)!=null)) {
					Optional<Quiz_Entity> quiz=Optional.of(quizdao.findByTitle(title));
					System.out.println(quiz.get());
					List<Question> questionsfromdb=quiz.get().getQuestion();
					
					for(Question q:questionsfromdb) {
						Question_Wrapper questionwrapper=new Question_Wrapper();
						questionwrapper.setId(q.getId());
						questionwrapper.setQuestionTitle(q.getQuestiontitle());
						questionwrapper.setOptions(q.getOptions());
						questionforuser.add(questionwrapper);

					}
					return new ResponseEntity<List<Question_Wrapper>>(questionforuser,HttpStatus.OK);
				}
			}
			catch (Exception e) {
				e.getStackTrace();
			}
			return new ResponseEntity<List<Question_Wrapper>>(questionforuser,HttpStatus.NO_CONTENT);
			
		
	}

	public ResponseEntity<List<Question_Wrapper>> getQuizQuestion(Integer id) {
		// TODO Auto-generated method stub
		List<Question_Wrapper> questionforuser=new ArrayList<>();
		try {
			if((quizdao.findById(id)!=null)) {
				Optional<Quiz_Entity> quiz=quizdao.findById(id);
				//System.out.println(quiz.get());
				List<Question> questionsfromdb=quiz.get().getQuestion();
				
				for(Question q:questionsfromdb) {
					Question_Wrapper questionwrapper=new Question_Wrapper();
					questionwrapper.setId(q.getId());
					questionwrapper.setQuestionTitle(q.getQuestiontitle());
					questionwrapper.setOptions(q.getOptions());
					questionforuser.add(questionwrapper);
				}
				return new ResponseEntity<List<Question_Wrapper>>(questionforuser,HttpStatus.OK);
			}
		}
		catch (Exception e) {
			e.getStackTrace();
		}
		return new ResponseEntity<List<Question_Wrapper>>(questionforuser,HttpStatus.NO_CONTENT);
	}

	public ResponseEntity<String> calculateresult(int id, List<Response_Question> responses) {
		// TODO Auto-generated method stub
		try {
			if(quizdao.findById(id)!=null) {
				Optional<Quiz_Entity> quiz=quizdao.findById(id);
				
				List<Question> questions=quiz.get().getQuestion();
				int index=0;
				int right=0;
				for(Response_Question response:responses) {
					if(response.getResponse().equals(questions.get(index).getRightanswer())) {
						right++;
					}
					index++;
				}
				int size=questions.size();
				//System.out.println("your "+right+"right answer out of "+size);
				return new ResponseEntity<String>("your "+right+" right answer out of "+size,HttpStatus.OK );
			}
		}
		catch(Exception e) {
			e.getStackTrace();
			
		}
		return new ResponseEntity<String>("Id is not found so score not calculate",HttpStatus.BAD_REQUEST );
		
		
		
		
	}

	public ResponseEntity<QuizQuestionUI> getdatauser(PostBasicData postdata) {
		// TODO Auto-generated method stub
		userJPA.save(postdata);
		Integer id=quizdao.findbyid(postdata.getCategory(),postdata.getCategorytopic());
		System.out.println("id of user is "+id);
		quizuserdata.setId(id);
		quizuserdata.setQuestion(getQuizQuestion(id).getBody());
		System.out.println(quizuserdata.getQuestion());
		return new ResponseEntity<QuizQuestionUI>(quizuserdata,HttpStatus.OK);
	}

	
	

}
