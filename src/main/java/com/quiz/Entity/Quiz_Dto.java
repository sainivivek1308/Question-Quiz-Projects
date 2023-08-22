package com.quiz.Entity;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quiz_Dto {
    int numq;
    String category;
    String title;
    String categorytopic;
    
}
