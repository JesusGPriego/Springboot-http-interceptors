package com.suleware.springboot.app.interceptor.springboot_interceptor.interceptors;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("loadingTimeInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptor.class);

    @Override
    public void postHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull Object handler,
            @Nullable ModelAndView modelAndView) throws Exception {
        String method = ((HandlerMethod) handler).getMethod().getName();

        long end = System.currentTimeMillis();

        long start = (long) request.getAttribute("start");

        long result = end - start;

        String tookTime = String.format("Time passed %sms", result);

        logger.info(tookTime);

        String data = String.format("LoadingTimeInterceptor: postHandle(), finishing %s", method);
        logger.info(data);
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull Object handler)
            throws Exception {
        String method = ((HandlerMethod) handler).getMethod().getName();
        String data = String.format("LoadingTimeInterceptor: preHandle(), init %s", method);
        logger.info(data);

        long start = System.currentTimeMillis();
        request.setAttribute("start", start);

        Random random = new Random();
        int delay = random.nextInt(500);

        Thread.sleep(delay);

        // Map<String, Object> json = new HashMap<>();
        // json.put("error", "Unauthorized access");
        // json.put("date", new Date());

        // ObjectMapper objectMapper = new ObjectMapper();
        // String jsonString = objectMapper.writeValueAsString(json);
        // response.setContentType(("application/json"));
        // response.setStatus(HttpStatus.UNAUTHORIZED.value());
        // response.getWriter().write(jsonString);

        // return false;
        return true;
    }

}
