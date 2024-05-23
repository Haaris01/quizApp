package com.quiz.quizservice.controller;


import com.quiz.quizservice.model.QuestionWrapper;
import com.quiz.quizservice.model.Quiz;
import com.quiz.quizservice.model.QuizDto;
import com.quiz.quizservice.model.Response;
import com.quiz.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @GetMapping("getAll")
    public List<Quiz> getAllQuiz(){
        return quizService.getAllQuiz();
    }
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id){
        return quizService.getQuiz(id);
    }

    @PostMapping("create")
    public ResponseEntity<Quiz> createQuiz(@RequestBody QuizDto quizDto){
        return quizService.createQuiz(quizDto);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> evaluateQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.evaluateQuiz(id, responses);
    }
}
