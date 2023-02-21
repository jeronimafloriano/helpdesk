package com.jeronima.helpdesk.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException{

    private static final long serialVersionUID = -3814700645453200953L;

    public ObjectNotFoundException(String message) {
        super(message);
    }
}
