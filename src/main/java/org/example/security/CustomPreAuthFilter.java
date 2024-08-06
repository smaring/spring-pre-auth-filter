package org.example.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;
import org.springframework.util.Assert;

public class CustomPreAuthFilter extends AbstractPreAuthenticatedProcessingFilter  {

  private String principalRequestParam = "email";

  private boolean exceptionIfParamMissing = true;


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
