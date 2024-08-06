package org.example.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class CustomPreAuthenticationDetails extends WebAuthenticationDetails {

  // store anything we want to pull out of the request

  public CustomPreAuthenticationDetails(HttpServletRequest request) {
    super(request);
  }

}
