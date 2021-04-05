package ru.centralhardware.testTask.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import ru.centralhardware.testTask.Id.IdGenerator;
import ru.centralhardware.testTask.web.CaptchaRestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Component
@Slf4j
public class RequestIdInterceptor implements HandlerInterceptor {

    public static final String REQUEST_ID_HEADER_NAME = "X-request-id";

    private final IdGenerator idGenerator;

    public RequestIdInterceptor(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    /**
     * set request id header
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestId = idGenerator.generateID();
        log.debug("request received. Id {}. Request detail: {}", requestId, request.toString());
        response.addHeader(REQUEST_ID_HEADER_NAME,  requestId);
        return true;
    }

    /**
     * delete captcha image file
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        new File(String.valueOf(request.getSession().getAttribute(CaptchaRestController.IMAGE_PATH_HEADER_NAME))).delete();
        log.debug("request {} complete", response.getHeader(REQUEST_ID_HEADER_NAME));
    }
}
