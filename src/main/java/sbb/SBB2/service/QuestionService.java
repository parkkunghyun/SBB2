package sbb.SBB2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sbb.SBB2.DataNofFoundException;
import sbb.SBB2.entity.Question;
import sbb.SBB2.repository.QuestionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public Page<Question> getList(int page){
        // 정수타입의 페이지 번호를 입력받아
        // 해당 페이지의 질문 목록을 리턴!
        // 10은 한 페이지당 보여줄 개수!
        Pageable pageable = PageRequest.of(page,10);
        return this.questionRepository.findAll(pageable);
    }
    //

    public Question getQuestion(Long id){
        Optional<Question> question = questionRepository.findById(id);
        if(question.isPresent()) {
            return question.get();
        }
        else{
            throw new DataNofFoundException("question not Found");
        }
    }
    public void create(String subject, String content) {
        Question question = new Question();
        question.setSubject(subject);
        question.setContent(content);
        question.setCreateDate(LocalDateTime.now());
        questionRepository.save(question);
    }
}
