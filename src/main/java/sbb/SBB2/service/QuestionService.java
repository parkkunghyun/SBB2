package sbb.SBB2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sbb.SBB2.DataNofFoundException;
import sbb.SBB2.entity.Question;
import sbb.SBB2.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> getList(){
        return questionRepository.findAll();
    }

    public Question getQuestion(Long id){
        Optional<Question> question = questionRepository.findById(id);
        if(question.isPresent()) {
            return question.get();
        }
        else{
            throw new DataNofFoundException("question not Found");
        }
    }
}
