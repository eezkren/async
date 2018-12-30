package com.isilona.async.entity;

public class SuccessJsonResponse extends JsonResponse {

    public SuccessJsonResponse() {
        this.success = true;
    }

    public SuccessJsonResponse(String message) {
        this();
        this.message = message;
    }
}
