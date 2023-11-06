package com.rj.backendjixian.exception;

import lombok.Getter;

@Getter
public class RolesAuthorizationException extends RuntimeException{
    private final String[] missingRole;
    public RolesAuthorizationException(String message, String[] missingRole) {
        super(message);
        this.missingRole = missingRole;
    }
}
