package sbb.SBB2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sbb.SBB2.entity.Question;
import sbb.SBB2.service.AnswerService;
import sbb.SBB2.service.QuestionService;

@Controller
@Slf4j
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;
    private final QuestionService questionService;

    @PostMapping("/create/{id}")
    public String createAnswer(@PathVariable("id") Long id, @RequestParam String content, Model model) {
        // 질문을 가져온후
        Question question = questionService.getQuestion(id);
        // 답변을 저장하기!
        answerService.create(question,content);
        return String.format("redirect:/question/detail/%s", id);
    }
}
