package sbb.SBB2;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "entity not found")
public class DataNofFoundException extends RuntimeException {
    private static final long serialVersionUID =1L;
    public DataNofFoundException(String message) {
        super(message);
    }

}
