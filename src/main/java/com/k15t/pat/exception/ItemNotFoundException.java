package com.k15t.pat.exception;


public class ItemNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -1949086824703943923L;

    public ItemNotFoundException(String message) {
        super(message);
    }

}
