package sbb.SBB2.question;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class QuestionForm {
    @NotEmpty(message = "제목은 필수항목")
    @Size(max = 200)
    private String subject;

    @NotEmpty(message = "내용 필수항목")
    private String content;
}
