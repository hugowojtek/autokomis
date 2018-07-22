package pl.sda.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(value=Exception.class)
    public ModelAndView defaultHandlerException(Model model, HttpServletRequest request, Exception e){
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception",e.getMessage());
        mav.addObject("url",request.getRequestURI());
        mav.setViewName(("error"));

        return mav;
    }
}
