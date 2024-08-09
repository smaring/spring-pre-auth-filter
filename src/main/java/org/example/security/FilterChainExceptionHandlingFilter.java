package org.example.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class FilterChainExceptionHandlingFilter extends OncePerRequestFilter {

  @Autowired
  private FilterChainExceptionResolver resolver;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
          throws ServletException, IOException {

    try {
      log.debug( "continue filter chain" );
      filterChain.doFilter(request, response);
      log.debug( "return from filter chain without exception" );
    } catch (Exception e) {
      log.warn(e.getLocalizedMessage());
      log.debug( "calling exception resolver" );
      resolver.resolveException(request, response, null, e);
      log.debug( "returned from exception resolver" );
      log.debug( "continue filter chain following exception handling" );
      filterChain.doFilter(request, response);
      log.debug( "return from filter chain following exception handling" );
    }

  }
}
