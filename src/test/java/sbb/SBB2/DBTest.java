package sbb.SBB2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sbb.SBB2.entity.Answer;
import sbb.SBB2.entity.Question;
import sbb.SBB2.repository.AnswerRepository;
import sbb.SBB2.repository.QuestionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest

public class DBTest {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Test
    void testJpa() {
        Question q1 = new Question();
        q1.setSubject("SBB가 뭔가요?");
        q1.setContent("sbb에 대해 알고 싶습니다");
        q1.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문입니다.");
        q2.setContent("id는 자동으로 생성되나요?");
        q2.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q2);  // 두번째 질문 저장
    }
    @Test
    void findAll() {
        List<Question> all = questionRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(2);

        Question q = all.get(0);
        System.out.println(q.getSubject());
    }
    @Test
    void findById(){
        Optional<Question> oq = this.questionRepository.findById(1L);
        if(oq.isPresent()) {
            Question q = oq.get();
            Assertions.assertThat(q.getSubject()).isEqualTo("SBB가 뭔가요?");
        }
    }
    @Test
    void findBySubject() {
        Question q= questionRepository.findBySubject("스프링부트 모델 질문입니다.");
        Assertions.assertThat(q.getId()).isEqualTo(2);
    }
    @Test
    void findBySubjectAndContent() {
        Question q = questionRepository.findBySubjectAndContent("스프링부트 모델 질문입니다.", "id는 자동으로 생성되나요?");
        Assertions.assertThat(q.getId()).isEqualTo(2);
    }
    @Test
    void findBySubjectLike() {
        List<Question> questionList = this.questionRepository.findBySubjectLike("sbb%");
        Question q = questionList.get(0);
        Assertions.assertThat(q.getId()).isEqualTo(1);
    }
    @Test
    void updateQuestion() {
        Optional<Question> oq = questionRepository.findById(1L);
        org.junit.jupiter.api.Assertions.assertTrue(oq.isPresent());

        Question q = oq.get();
        q.setSubject("수정된 제목");
        questionRepository.save(q);
    }
    @Test
    void deleteQuestion() {
        Optional<Question> qlist = questionRepository.findById(1L);
        org.junit.jupiter.api.Assertions.assertTrue(qlist.isPresent());

        Question q = qlist.get();
        questionRepository.delete(q);
        Assertions.assertThat(1).isEqualTo(questionRepository.count());
    }

    @Test
    void createAnswer(){
        Optional<Question> oq = questionRepository.findById(2L);

        Question q = oq.get();

        Answer a = new Answer();
        a.setContent("네 자동생성임돠");
        a.setCreateDate(LocalDateTime.now());
        a.setQuestion(q);
        answerRepository.save(a);
    }

    @Transactional
    @Test
    void findAnswerQuestion(){
        Optional<Question> oq = this.questionRepository.findById(2L);
        Question q1 =  oq.get();
        List<Answer> answerList = q1.getAnswerList();
        Assertions.assertThat(answerList.size()).isEqualTo(1);

    }



}
