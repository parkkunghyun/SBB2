package sbb.SBB2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sbb.SBB2.answer.AnswerForm;
import sbb.SBB2.entity.Question;
import sbb.SBB2.question.QuestionForm;
import sbb.SBB2.repository.QuestionRepository;
import sbb.SBB2.service.QuestionService;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    private  final QuestionService questionService;
    @GetMapping("/list")
    public String list(Model model) {
        List<Question> questionList = questionService.getList();
        model.addAttribute("questionList", questionList);
        return "question/list";
    }
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model, AnswerForm answerForm){
        Question question = questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question/question_detail";
    }
    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm){
        return "question/question_form";
    }
    @PostMapping("/create")
    public String questionSave(@Validated QuestionForm questionForm,
                               BindingResult bindingResult) {
        // spring의 바인딩 기술!
        if(bindingResult.hasErrors()) {
            return "question/question_form";
        }
        // question 저장하기
        questionService.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list";
    }

}
