package com.test.common.listener;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class MetricsRetryListener implements RetryListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetricsRetryListener.class);

    @Override
    public <T, E extends Throwable> boolean open(RetryContext retryContext, RetryCallback<T, E> retryCallback) {
        return true;
    }

    @Override
    public <T, E extends Throwable> void close(RetryContext retryContext, RetryCallback<T, E> retryCallback, Throwable throwable) {
        // TODO : add metrics
        String status = Objects.isNull(throwable) ? "SUCC" : "FAIL";
        String clientName = "";
        if (Objects.nonNull(retryContext)) {
            clientName = (String) retryContext.getAttribute(RetryContext.NAME);
        }

        LOGGER.info("Client {} call {}", clientName, status);
    }

    @Override
    public <T, E extends Throwable> void onError(RetryContext retryContext, RetryCallback<T, E> retryCallback, Throwable throwable) {
        LOGGER.warn("Client execute fail, current retry count = {}", Objects.nonNull(retryContext) ? retryContext.getRetryCount() : 0);
    }
}
