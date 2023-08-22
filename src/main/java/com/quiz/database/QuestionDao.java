package com.quiz.database;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quiz.Entity.Question;

import jakarta.persistence.Id;
import jakarta.transaction.Transactional;

@Repository
public interface QuestionDao extends JpaRepository<Question, Id>{
	@Query(value="select * from question q where id=:id",nativeQuery=true)
	Question findbyIID(@Param(value="id") int id);
	
	@Modifying
	@Transactional
	@Query(value = "delete from question where id=:id",nativeQuery = true)
	void deleteById(@Param("id") int id1);

//	@Query(value="select q.id,q.category,q.difficultylevel,q.option1,"
//			+ "q.option2,q.option3,q.option4,q.question_title,q.right_answer from quiz q where q.category=:category",nativeQuery=true)
	List<Question> findByCategory(String category);

	@Query(value = "SELECT * FROM question where category=:category AND categorytopic=:categorytopic ORDER BY RANDOM() LIMIT :numq",nativeQuery = true)
	List<Question> findrandombyCategory(@Param("category") String category,@Param("numq") int numq,@Param("categorytopic") String categorytopic) ;

	}


