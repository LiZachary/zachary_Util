package com.zachary.util.Net.retry;

import java.io.IOException;

/**
 * Default retry policy for requests.
 */
public class DefaultRetryPolicy implements RetryPolicy {
    /**
     * The current timeout in milliseconds.
     */
    private int mCurrentTimeoutMs;

    /**
     * The current retry count.
     */
    private int mCurrentRetryCount;

    /**
     * The maximum number of attempts.
     */
    private final int mMaxNumRetries;

    /**
     * The backoff multiplier for the policy.
     */
    private final float mBackoffMultiplier;

    /**
     * The default socket timeout in milliseconds
     */
    public static final int DEFAULT_TIMEOUT_MS = 5000;

    /**
     * The default number of retries
     */
    public static final int DEFAULT_MAX_RETRIES = 1;

    /**
     * The default backoff multiplier
     */
    public static final float DEFAULT_BACKOFF_MULT = 1f;

    /**
     * Constructs a new retry policy using the default timeouts.
     */
    public DefaultRetryPolicy() {
        this(DEFAULT_TIMEOUT_MS, DEFAULT_MAX_RETRIES, DEFAULT_BACKOFF_MULT, 0);
    }

    /**
     * Constructs a new retry policy.
     *
     * @param initialTimeoutMs  The initial timeout for the policy.
     * @param maxNumRetries     The maximum number of retries.
     * @param backoffMultiplier Backoff multiplier for the policy.
     */
    public DefaultRetryPolicy(int initialTimeoutMs, int maxNumRetries, float backoffMultiplier, int currentRetryCount) {
        mCurrentTimeoutMs = initialTimeoutMs;
        mMaxNumRetries = maxNumRetries;
        mBackoffMultiplier = backoffMultiplier;
        mCurrentRetryCount = currentRetryCount;
    }

    @Override
    public void setCurrentTimeoutMs(int currentTimeoutMs) {
        this.mCurrentTimeoutMs = currentTimeoutMs;
    }

    @Override
    public void setCurrentRetryCount(int currentRetryCount) {
        this.mCurrentRetryCount = currentRetryCount;
    }

    /**
     * Returns the current timeout.
     */
    @Override
    public int getCurrentTimeout() {
        return mCurrentTimeoutMs;
    }

    /**
     * Returns the current retry count.
     */
    @Override
    public int getCurrentRetryCount() {
        return mCurrentRetryCount;
    }

    /**
     * Returns the backoff multiplier for the policy.
     */
    public float getBackoffMultiplier() {
        return mBackoffMultiplier;
    }

    /**
     * Prepares for the next retry by applying a backoff to the timeout.
     *
     * @param error The error code of the last attempt.
     */
    @Override
    public void retry(IOException error) throws IOException {
        mCurrentRetryCount++;
        mCurrentTimeoutMs += (mCurrentTimeoutMs * mBackoffMultiplier);
        if (!hasAttemptRemaining()) {
            throw error;
        }

    }

    /**
     * Returns true if this policy has attempts remaining, false otherwise.
     */
    protected boolean hasAttemptRemaining() {
        return mCurrentRetryCount <= mMaxNumRetries;
    }
}
