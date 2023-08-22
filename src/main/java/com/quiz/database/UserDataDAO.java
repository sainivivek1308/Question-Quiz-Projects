package com.quiz.database;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quiz.Entity.Frontend.PostBasicData;

@Repository

public interface UserDataDAO  extends JpaRepository<PostBasicData, Integer>{

	

}
