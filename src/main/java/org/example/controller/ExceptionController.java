package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
//@ControllerAdvice
public class ExceptionController {

  /*
  @ExceptionHandler(Exception.class)
  public ModelAndView handleException(Exception ex ) {
    log.info( "handling exception from filter chain" );
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("error");
    modelAndView.getModel().put( "message", ex.getLocalizedMessage() );
    return modelAndView;
  }
*/
}
