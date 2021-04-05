package ru.centralhardware.testTask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class RequestIdInterceptor implements HandlerInterceptor {

    public static final String REQUEST_ID_HEADER_NAME = "X-request_id";

    private final IdGenerator idGenerator;

    public RequestIdInterceptor(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestId = idGenerator.generateID();
        log.debug("request received. Id {}. Request detail: {}", requestId, request.toString());
        response.addHeader(REQUEST_ID_HEADER_NAME,  requestId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.debug("request {} complete", response.getHeader(REQUEST_ID_HEADER_NAME));
    }
}