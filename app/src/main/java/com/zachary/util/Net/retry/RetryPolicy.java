/*
 * Copyright (C) 2015 House365. All rights reserved.
 */

package com.zachary.util.Net.retry;

import java.io.IOException;
import java.net.HttpRetryException;

/**
 * Retry policy for a request.
 */
public interface RetryPolicy {

    void setCurrentTimeoutMs(int currentTimeoutMs);

    void setCurrentRetryCount(int currentRetryCount);

    /**
     * Returns the current timeout (used for logging).
     */
    int getCurrentTimeout();

    /**
     * Returns the current retry count (used for logging).
     */
    int getCurrentRetryCount();

    /**
     * Prepares for the next retry by applying a backoff to the timeout.
     *
     * @param error The error code of the last attempt.
     * @throws HttpRetryException In the event that the retry could not be performed (for example if we
     *                            ran out of attempts), the passed in error is thrown.
     */
    void retry(IOException error) throws IOException;
}
