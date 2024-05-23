package com.quiz.quizservice.service;

import com.quiz.quizservice.dao.QuizDao;
import com.quiz.quizservice.feign.QuizInterface;
import com.quiz.quizservice.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;
    @Autowired
    QuizInterface quizInterface;
    public ResponseEntity<Quiz> createQuiz(QuizDto quizDto){
        List<Integer> questions = quizInterface.generateQuestionsForQuiz(quizDto).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(quizDto.getTitle());
        quiz.setQuestions((questions));
        quizDao.save(quiz);
        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {
        Quiz quiz = quizDao.findById(id).get();
//        System.out.println(quiz.getQuestions());
        List<QuestionWrapper> questions = quizInterface.getQuestionsWithIds(quiz.getQuestions()).getBody();

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public List<Quiz> getAllQuiz() {
        return quizDao.findAll();
    }

    public ResponseEntity<Integer> evaluateQuiz(Integer id, List<Response> responses) {
        Integer score = quizInterface.calculateScore(responses).getBody();
        return new ResponseEntity<>(score, HttpStatus.OK);
    }
}
