package org.example.config;

import org.example.security.FilterChainExceptionResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

  @Override
  public void configureHandlerExceptionResolvers( List<HandlerExceptionResolver> resolvers ) {
    resolvers.add(0, new FilterChainExceptionResolver());
  }

}
