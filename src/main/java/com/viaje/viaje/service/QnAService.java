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

    public List<Questions> questionsList(){
        return questionsRepository.findAll();
    }

    public List<Answers> answersList(){
        return answersRepository.findAll();
    }

    @Transactional
    public void addAnswer(Long questionId, String content, Users user) {
        Questions question = questionsRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        if (question.getAnswer() != null) {
            throw new RuntimeException("This question already has an answer");
        }

        Answers answer = new Answers();
        answer.setContents(content);
        answer.setUser(user);
        answer.setQuestions(question);

        answersRepository.save(answer);

        question.setAnswer(answer);
        question.setQnaStatus(Questions.QnaStatus.답변완료);
        questionsRepository.save(question);
    }

    public void postQuestion(QuestionsDTO questionsDTO,Users user) {
        Questions newQuestion = new Questions();
        newQuestion.setUser(user);
        newQuestion.setTitle(questionsDTO.getTitle());
        newQuestion.setContents(questionsDTO.getContents());

        questionsRepository.save(newQuestion);

    }
}
