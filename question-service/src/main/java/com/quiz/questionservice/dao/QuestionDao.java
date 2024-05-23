package com.quiz.questionservice.dao;

import com.quiz.questionservice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionDao extends JpaRepository<Question, String> {
    public List<Question> getQuestionsByCategory(String category);
    @Query(value = "SELECT DISTINCT * FROM Question ORDER BY RAND() LIMIT :number", nativeQuery = true)
    public List<Question> getRandomQuestions(int number);

    @Query(value = "SELECT DISTINCT id FROM Question q WHERE q.category = :category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
    public List<Integer> getRandomQuestionsByCategory(String category, int numQ);
}
