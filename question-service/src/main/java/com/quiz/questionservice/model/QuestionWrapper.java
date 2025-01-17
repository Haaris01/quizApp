package com.quiz.questionservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionWrapper {
    private Integer id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
}
