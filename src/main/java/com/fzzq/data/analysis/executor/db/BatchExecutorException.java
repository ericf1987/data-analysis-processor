package com.fzzq.data.analysis.executor.db;

/**
 * Created by fengye on 2017/8/7.
 */
public class BatchExecutorException extends RuntimeException {

    public BatchExecutorException() {
    }

    public BatchExecutorException(String message) {
        super(message);
    }

    public BatchExecutorException(String message, Throwable cause) {
        super(message, cause);
    }

    public BatchExecutorException(Throwable cause) {
        super(cause);
    }
}

