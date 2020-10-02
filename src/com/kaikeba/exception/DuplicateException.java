package com.kaikeba.exception;

/**
 * 复本代码异常
 *
 * @author Faker
 * @date 2020/10/01
 */
public class DuplicateException extends Exception{
    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public DuplicateException() {
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public DuplicateException(String message) {
        super(message);
    }
}
