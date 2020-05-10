package org.codingsquid.r2dbc.exception;

import org.springframework.http.HttpStatus;

/**
 * @author hubert.squid
 * @since 2020.05.10
 */
public class NotFoundEntityException extends BaseException {

    public NotFoundEntityException(String message) {
        super(HttpStatus.NOT_FOUND.value(), message);
    }

    public NotFoundEntityException() {
        this("Not found entity");
    }
}
