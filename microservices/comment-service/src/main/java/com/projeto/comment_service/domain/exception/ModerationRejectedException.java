package com.projeto.comment_service.domain.exception;



public class ModerationRejectedException extends RuntimeException{
    public ModerationRejectedException(String message) {
        super(message);
    }
}
