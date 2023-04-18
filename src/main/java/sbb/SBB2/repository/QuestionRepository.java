package sbb.SBB2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbb.SBB2.entity.Question;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question findBySubject(String subject);

    Question findBySubjectAndContent(String subject, String content);

    List<Question> findBySubjectLike(String subject);



}