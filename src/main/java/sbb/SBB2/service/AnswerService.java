package sbb.SBB2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sbb.SBB2.entity.Answer;
import sbb.SBB2.entity.Question;
import sbb.SBB2.repository.AnswerRepository;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnswerService {
    private final QuestionService questionService;
    private final AnswerRepository answerRepository;

    public void create(Question question, String content) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answerRepository.save(answer);

    }

}
