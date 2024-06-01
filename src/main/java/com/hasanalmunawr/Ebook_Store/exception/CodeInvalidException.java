package com.hasanalmunawr.Ebook_Store.exception;


public class CodeInvalidException extends RuntimeException {

    public CodeInvalidException(String message) {
        super(message);
    }

    public CodeInvalidException() {
        super("Invalid code");
    }
}
