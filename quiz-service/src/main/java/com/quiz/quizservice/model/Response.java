package com.quiz.quizservice.model;

import lombok.Data;

@Data
public class Response {
    private int questionId;
    private String choosenAnswer;
}
