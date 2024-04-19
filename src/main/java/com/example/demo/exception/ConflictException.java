package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends BaseException {

    public ConflictException(String msg) {
        super(
                msg,
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT);
    }

}
