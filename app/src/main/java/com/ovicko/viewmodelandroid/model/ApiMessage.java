/*
 * Copyright (c) Amwollo Victor 2019.
 */

package com.ovicko.viewmodelandroid.model;

public class ApiMessage {
    String message;
    Throwable throwable;

    public ApiMessage(String message) {
        this.message = message;
    }

    public ApiMessage(Throwable throwable) {
        this.throwable = throwable;
    }

    public String getMessage() {
        return message;
    }
}
