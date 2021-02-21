package com.dxn.expections;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author dxn
 * @version 1.0
 * @date 2021/1/21 22:26
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(ex);
        modelAndView.setViewName("/error");
        modelAndView.addObject("errorMsg",ex.getMessage());
        return modelAndView;
    }
}
