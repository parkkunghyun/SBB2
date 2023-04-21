package sbb.SBB2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sbb.SBB2.service.QuestionService;

@SpringBootTest
public class PagingTest {
    @Autowired
    private QuestionService questionService;

    @Test
    void testJPA() {
        for(int i =0; i<300; i++) {
            String subject = String.format("테스트 데이터입니다: [%03d]",i);
            String content ="내용 무";
            questionService.create(subject,content);
        }
    }

}
