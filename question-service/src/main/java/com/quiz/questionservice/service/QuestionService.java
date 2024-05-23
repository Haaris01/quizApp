package com.quiz.questionservice.service;

import com.quiz.questionservice.dao.QuestionDao;
import com.quiz.questionservice.model.Question;
import com.quiz.questionservice.model.QuestionWrapper;
import com.quiz.questionservice.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getQuestion(){
        List<Question> questions;
        questions = questionDao.findAll();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        System.out.println(question);
        questionDao.save(question);
        return new ResponseEntity<>("Question Added", HttpStatus.CREATED);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        return new ResponseEntity<>(questionDao.getQuestionsByCategory(category), HttpStatus.OK);
    }

    public void addMultipleQuestions(List<Question> questions) {
        for(Question q : questions){
            questionDao.save(q);
        }
    }

    public List<Question> getRandomQuestions(int number) {
        List<Question> questions = questionDao.getRandomQuestions(number);
        return questions;
    }

    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(String category, int numQuestions){
        List<Integer> questions = questionDao.getRandomQuestionsByCategory(category, numQuestions);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsWithIds(List<Integer> ids){
        List<QuestionWrapper> wrappers = new ArrayList<>();
        for(Integer i : ids){
//            System.out.println(i);
            Question question = questionDao.findById(String.valueOf(i)).get();
            QuestionWrapper questionWrapper = QuestionWrapper.builder()
                                                            .id(question.getId())
                                                            .questionTitle(question.getQuestionTitle())
                                                            .option1(question.getOption1())
                                                            .option2(question.getOption2())
                                                            .option3(question.getOption3())
                                                            .option4(question.getOption4())
                                                            .build();
            wrappers.add(questionWrapper);
        }
//        System.out.println(wrappers);
        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateScore(List<Response> responses) {
        Integer score = 0;
        System.out.println(responses);
        for(Response response : responses){
            Question question = questionDao.findById(String.valueOf(response.getQuestionId())).get();
            if(response.getChoosenAnswer().equals(question.getRightAnswer()))
                score++;
        }
        return new ResponseEntity<>(score, HttpStatus.OK);
    }
}
