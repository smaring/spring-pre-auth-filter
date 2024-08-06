package org.example.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class CustomPreAuthSuccessHandler implements AuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    request.getSession().setAttribute("email", authentication.getPrincipal() );
    request.getSession().setAttribute("name", request.getParameter("name") );
    request.getSession().setAttribute("id", request.getParameter("id") );
    request.getSession().setAttribute("avatar_url", request.getParameter("avatar_url") );
  }

}
