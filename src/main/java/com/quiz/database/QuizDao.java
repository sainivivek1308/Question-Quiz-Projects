package com.quiz.database;


import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quiz.Entity.Quiz_Entity;
import com.quiz.Entity.Frontend.PostBasicData;
@Repository
public interface QuizDao extends JpaRepository<Quiz_Entity, Integer> {

	Quiz_Entity findByTitle(String title);
	@Query(value = "SELECT id FROM quiz where category=:category AND categorytopic=:categorytopic",nativeQuery = true)
	Integer findbyid(@Param("category")String  category,@Param("categorytopic") String categorytopic);
	

	

}
