package org.socgen.http;

import org.socgen.constants.HttpMethod;

public class HttpRequest extends HttpMessage{

    private HttpMethod method;
    private String requestTarget;
    private String httpVersion;

    HttpRequest() {}

    public HttpMethod getMethod() {
        return method;
    }

    void setMethod(String methodName) {
        this.method = HttpMethod.valueOf(methodName);
    }
}
