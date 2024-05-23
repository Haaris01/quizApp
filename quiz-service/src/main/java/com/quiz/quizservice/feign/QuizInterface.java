package com.quiz.quizservice.feign;

import com.quiz.quizservice.model.QuestionWrapper;
import com.quiz.quizservice.model.QuizDto;
import com.quiz.quizservice.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(@RequestBody QuizDto quizDto);

    //getQuestions
    @PostMapping("question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsWithIds(@RequestBody List<Integer> ids);

    //calculateScore
    @PostMapping("question/calculateScore")
    public ResponseEntity<Integer> calculateScore(@RequestBody List<Response> responses);
}
