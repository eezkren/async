package com.isilona.async.services;

import com.isilona.async.entity.ErrorJsonResponse;
import com.isilona.async.entity.JsonResponse;
import com.isilona.async.entity.SuccessJsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {

    private Logger logger = LoggerFactory.getLogger(AsyncService.class);

    @Async
    public CompletableFuture<JsonResponse> deferredCompletableFuture() {

        logger.info("ChildThread-{}", Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(new SuccessJsonResponse("hello deferred"));

    }

    @Async
    public CompletableFuture<JsonResponse> deferredCompletableFutureTimedOut() {

        logger.info("ChildThread-{}", Thread.currentThread().getName());
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(new SuccessJsonResponse("hello deferred"));

    }

    @Async
    public CompletableFuture<JsonResponse> deferredCompletableFutureException() {

        logger.info("ChildThread-{}", Thread.currentThread().getName());
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(new ErrorJsonResponse(e.getMessage()));
        }
        return CompletableFuture.completedFuture(new SuccessJsonResponse("hello deferred"));

    }
}
