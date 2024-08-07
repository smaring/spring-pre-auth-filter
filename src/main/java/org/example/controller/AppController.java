package org.example.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Controller
public class AppController {

  @GetMapping("/")
  public String indexPage() {
    log.debug( "show index.html" );
    return "index";
  }

  @GetMapping("/page2")
  public String pagee2() {
    log.debug( "show page2" );
    return "page2";
  }

  @GetMapping("/error")
  public String error(HttpServletRequest request,
                      Model model) {
    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

    if (status != null) {
      Integer statusCode = Integer.valueOf(status.toString());
      model.addAttribute( "statusCode", statusCode );
    }

    return "error";
  }

}
