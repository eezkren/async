package com.isilona.async;

import com.isilona.async.entity.ErrorJsonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

public class AsyncApplicationDeferredResult<T> extends DeferredResult<T> {

    public AsyncApplicationDeferredResult() {
        super(5000L, ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT)
                .body(new ErrorJsonResponse("onTimeout: Request timeout occurred.")));
    }
}
