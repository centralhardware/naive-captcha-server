package ru.centralhardware.testTask.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.centralhardware.testTask.web.RequestIdInterceptor;

@Component
public class WebMvcConfig implements WebMvcConfigurer {

    private final RequestIdInterceptor interceptor;

    public WebMvcConfig(RequestIdInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
    }
}
