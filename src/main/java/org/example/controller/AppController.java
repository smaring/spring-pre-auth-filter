package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Controller
public class AppController {

  @GetMapping("/")
  public String indexPage() {
    log.debug( "show index.html" );
    return "index.html";
  }

  @GetMapping("/page2")
  public String pagee2() {
    log.debug( "show page2" );
    return "page2.html";
  }

}
