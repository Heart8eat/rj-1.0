package com.rj.backendjixian.exception;

import lombok.Getter;

@Getter
public class LoginException extends Exception {
    private final String loginName;

    public LoginException(String message, String loginName) {
        super(message);
        this.loginName = loginName;
    }

}
