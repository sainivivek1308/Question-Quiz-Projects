package com.quiz.Entity;

import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="Quiz")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quiz_Entity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private String categorytopic;
	private String category;
	@ManyToMany
	private List<Question> question;
	public Integer getId() {
		return id;
	}
		
	

}
