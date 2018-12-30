package com.isilona.async.controllers;

import com.isilona.async.AsyncApplicationDeferredResult;
import com.isilona.async.entity.JsonResponse;
import com.isilona.async.services.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/")
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    private Logger logger = LoggerFactory.getLogger(AsyncController.class);

    @GetMapping(value = "/sync")
    public String getSyncResource() throws InterruptedException {
        logger.info("ThreadName-{}.", Thread.currentThread().getName());
        Thread.sleep(10000);
        return "hello sync";
    }

    @GetMapping(value = "/deferred")
    public DeferredResult<ResponseEntity<String>> getDeferredResource() {
        logger.info("ThreadName-{}", Thread.currentThread().getName());
        DeferredResult<ResponseEntity<String>> deferredResult = new DeferredResult<>();

        CompletableFuture.supplyAsync(() -> {
            logger.info("ChildThread-{}", Thread.currentThread().getName());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello deferred";
        }).whenCompleteAsync((result, throwable) -> deferredResult.setResult(new ResponseEntity(result, HttpStatus.OK)));

        return deferredResult;

    }

    @GetMapping(value = "/deferredWithService")
    public DeferredResult<JsonResponse> getDeferredResourceWithService() {
        logger.info("ThreadName-{}", Thread.currentThread().getName());
        DeferredResult<JsonResponse> deferredResult = new AsyncApplicationDeferredResult<>();

        asyncService.deferredCompletableFuture()
                .whenCompleteAsync((result, throwable) -> deferredResult.setResult(result));


        return deferredResult;

    }

    @GetMapping(value = "/deferredWithServiceTimedOut")
    public DeferredResult<JsonResponse> getDeferredResourceWithServiceTimedOut() {
        logger.info("ThreadName-{}", Thread.currentThread().getName());
        DeferredResult<JsonResponse> deferredResult = new AsyncApplicationDeferredResult<>();

        asyncService.deferredCompletableFutureTimedOut()
                .whenCompleteAsync((result, throwable) -> deferredResult.setResult(result));

        return deferredResult;

    }

    @GetMapping(value = "/deferredWithServiceException")
    public DeferredResult<JsonResponse> getDeferredResourceWithServiceException() {
        logger.info("ThreadName-{}", Thread.currentThread().getName());
        DeferredResult<JsonResponse> deferredResult = new AsyncApplicationDeferredResult<>();

        asyncService.deferredCompletableFutureException()
                .whenCompleteAsync((result, throwable) -> deferredResult.setResult(result));


        return deferredResult;

    }

}
