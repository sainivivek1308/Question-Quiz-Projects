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
@Profile("postsql")
@Repository
public interface QuestionDao extends JpaRepository<Question, Id>{
	@Query(value="select * from quiz q where id=:id",nativeQuery=true)
	Question findbyIID(@Param(value="id") int id);
	
	@Modifying
	@Transactional
	@Query(value = "delete from quiz where id=:id",nativeQuery = true)
	void deleteById(@Param("id") int id1);

//	@Query(value="select q.id,q.category,q.difficultylevel,q.option1,"
//			+ "q.option2,q.option3,q.option4,q.question_title,q.right_answer from quiz q where q.category=:category",nativeQuery=true)
	List<Question> findByCategory(String category);

	@Query(value = "SELECT * FROM quiz Q where q.category=:category ORDER BY RANDOM() LIMIT :numq",nativeQuery = true)
	List<Question> findrandombyCategory(@Param("category") String category,@Param("numq") int numq) ;

	}


