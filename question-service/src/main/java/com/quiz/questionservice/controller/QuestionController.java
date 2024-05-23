package com.quiz.questionservice.controller;

import com.quiz.questionservice.model.Question;
import com.quiz.questionservice.model.QuestionWrapper;
import com.quiz.questionservice.model.QuizDto;
import com.quiz.questionservice.model.Response;
import com.quiz.questionservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("question")
public class QuestionController {


    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getQuestion(){
        return questionService.getQuestion();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        questionService.addQuestion(question);
        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

    @PostMapping("addMultiple")
    public ResponseEntity<String> addMultipleQuestions(@RequestBody List<Question> questions){
        questionService.addMultipleQuestions(questions);
        return new ResponseEntity<>("Added all the question", HttpStatus.CREATED);
    }

    @GetMapping("getRandom/{number}")
    public ResponseEntity<List<Question>> getRandomQuestions(@PathVariable int number){
        return new ResponseEntity<>(questionService.getRandomQuestions(number), HttpStatus.OK);
    }

    //generate
    @PostMapping("generate")
    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(@RequestBody QuizDto quizDto){
        return questionService.generateQuestionsForQuiz(quizDto.getCategory(), quizDto.getNumQuestions());
    }

    //getQuestions
    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsWithIds(@RequestBody List<Integer> ids){
        return questionService.getQuestionsWithIds(ids);
    }

    //calculateScore
    @PostMapping("calculateScore")
    public ResponseEntity<Integer> calculateScore(@RequestBody List<Response> responses){
        return questionService.calculateScore(responses);
    }

}
