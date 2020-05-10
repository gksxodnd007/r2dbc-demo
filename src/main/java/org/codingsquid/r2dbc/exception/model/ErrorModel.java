package org.codingsquid.r2dbc.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.codingsquid.r2dbc.exception.BaseException;
import org.springframework.http.HttpStatus;

/**
 * @author hubert.squid
 * @since 2020.05.10
 */
@Getter
@Setter
@AllArgsConstructor
public class ErrorModel {

    private int errorCode;
    private String errorMessage;

    public static ErrorModel from(BaseException exception) {
        return new ErrorModel(exception.getErrorCode(), exception.getErrorMessage());
    }

    public static ErrorModel unknown() {
        return new ErrorModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }
}
