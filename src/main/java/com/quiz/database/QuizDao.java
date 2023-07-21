package com.quiz.database;


import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quiz.Entity.Quiz_Entity;
@Repository
@Profile("postsql")
public interface QuizDao extends JpaRepository<Quiz_Entity, Integer> {

	Quiz_Entity findByTitle(String title);

	

}
