package com.isilona.async.entity;

public class ErrorJsonResponse extends JsonResponse {

    public ErrorJsonResponse() {
        this.success = false;
    }

    public ErrorJsonResponse(String message) {
        this();
        this.message = message;
    }

}
