package org.socgen.exception;

import org.socgen.constants.HttpStatusCodes;

public class HttpParsingException extends Exception {

    private HttpStatusCodes errorCode;

    public HttpParsingException(HttpStatusCodes errorCode) {
        super(errorCode.MESSAGE);
        this.errorCode = errorCode;
    }

    public HttpStatusCodes getErrorCode() {
        return errorCode;
    }
}
