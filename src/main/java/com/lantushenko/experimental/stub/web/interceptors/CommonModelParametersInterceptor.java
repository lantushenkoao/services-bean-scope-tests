package com.lantushenko.experimental.stub.web.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lantushenko.experimental.stub.web.ModelParamNames;

/**
 * Include appName parameter into all views to make it avaliable everywhere
 */
public class CommonModelParametersInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    @Qualifier("app.name")
    private String applicationName;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        modelAndView.getModelMap().addAttribute(ModelParamNames.APPLICATION_NAME, applicationName);
    }
}
