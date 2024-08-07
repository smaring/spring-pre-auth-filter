package org.example.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.log.LogMessage;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.util.List;

@Slf4j
public class CustomPreAuthFilter extends AbstractPreAuthenticatedProcessingFilter  {

  private List<String> validReferers;

  private String principalRequestParam = "email";

  private boolean exceptionIfParamMissing = true;

  public CustomPreAuthFilter( List<String> validReferers ) {
    this.validReferers = validReferers;
  }

  @Override
  @ResponseStatus(value= HttpStatus.FORBIDDEN, reason="Direct access not permitted")
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException {

    HttpServletRequest httpRequest = (HttpServletRequest) request;
    String referer = httpRequest.getHeader( "Referer" );
    HttpSession httpSession = httpRequest.getSession(false);
    if ( httpSession == null ) {
      log.debug( "no active session yet" );
      boolean isValidReferer = false;
      if ( referer != null ) {
        for (String validReferer : this.validReferers) {
          if (referer.startsWith(validReferer)) {
            isValidReferer = true;
          }
        }
      }
      if ( referer == null || !isValidReferer ) {
        throw new ServletException( "Direct access not permitted" );
      }
    }

    super.doFilter( request, response, chain );
  }

  @Override
  protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
    String principal = request.getParameter(this.principalRequestParam);
    if (principal == null && this.exceptionIfParamMissing) {
      throw new PreAuthenticatedCredentialsNotFoundException(
              this.principalRequestParam + " param not found in request.");
    }
    return principal;
  }

  @Override
  protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
    return "N/A";
  }

  public void setPrincipalRequestParam(String principalRequestParam) {
    Assert.hasText(principalRequestParam, "principalRequestParam must not be empty or null");
    this.principalRequestParam = principalRequestParam;
  }


  public void setExceptionIfParamMissing(boolean exceptionIfParamMissing) {
    this.exceptionIfParamMissing = exceptionIfParamMissing;
  }

}
