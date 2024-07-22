package com.viaje.viaje.service;

import com.viaje.viaje.dto.QuestionsDTO;
import com.viaje.viaje.model.Answers;
import com.viaje.viaje.model.Questions;
import com.viaje.viaje.model.Users;
import com.viaje.viaje.repository.AnswersRepository;
import com.viaje.viaje.repository.QuestionsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnAService {

    private final QuestionsRepository questionsRepository;
    private final AnswersRepository answersRepository;

    public QnAService(QuestionsRepository questionsRepository, AnswersRepository answersRepository) {
        this.questionsRepository = questionsRepository;
        this.answersRepository = answersRepository;
    }

    public List<Questions> questionsList() {
        return questionsRepository.findAll();
    }

    public List<Answers> answersList() {
        return answersRepository.findAll();
    }

    @Transactional
    public void addAnswer(Long questionsId, String content, Users user) {
        Questions question = questionsRepository.findById(questionsId)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        if (question.getAnswers() != null && !question.getAnswers().isEmpty()) {
            throw new RuntimeException("This question already has an answer");
        }

        Answers answer = new Answers();
        answer.setContents(content);
        answer.setUser(user);
        answer.setQuestions(question);

        answersRepository.save(answer);

        question.addAnswer(answer);
        question.setQnaStatus(Questions.QnaStatus.답변완료);
        questionsRepository.save(question);
    }

    public void postQuestion(QuestionsDTO questionsDTO, Users user) {
        Questions newQuestion = new Questions();
        newQuestion.setUser(user);
        newQuestion.setTitle(questionsDTO.getTitle());
        newQuestion.setContents(questionsDTO.getContents());

        questionsRepository.save(newQuestion);

    }

    @Transactional
    public void updateQuestion(Long questionsId, QuestionsDTO questionsDTO, Users user) {
        if (questionsId == null) {
            throw new IllegalArgumentException("Question ID cannot be null");
        }
        Questions question = questionsRepository.findById(questionsId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        if (!question.getUser().equals(user)) {
            throw new RuntimeException("You are not authorized to update this question");
        }

        question.setTitle(questionsDTO.getTitle());
        question.setContents(questionsDTO.getContents());

        questionsRepository.save(question);
    }

    @Transactional
    public void deleteQuestion(Long questionsId, Users user) {
        Questions question = questionsRepository.findById(questionsId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        if (!question.getUser().equals(user)) {
            throw new RuntimeException("You are not authorized to delete this question");
        }

        questionsRepository.delete(question);
    }

    @Transactional
    public void updateAnswer(Long answerId, String content, Users user) {
        Answers answer = answersRepository.findById(answerId)
                .orElseThrow(() -> new RuntimeException("Answer not found"));

        if (!answer.getUser().equals(user)) {
            throw new RuntimeException("You are not authorized to update this answer");
        }

        answer.setContents(content);
        answersRepository.save(answer);
    }

    @Transactional
    public void deleteAnswer(Long answerId, Users user) {
        Answers answer = answersRepository.findById(answerId)
                .orElseThrow(() -> new RuntimeException("Answer not found"));

        if (!answer.getUser().equals(user)) {
            throw new RuntimeException("You are not authorized to delete this answer");
        }

        answersRepository.delete(answer);
    }
}
